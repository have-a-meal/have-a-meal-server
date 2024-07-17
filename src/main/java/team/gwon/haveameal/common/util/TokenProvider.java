package team.gwon.haveameal.common.util;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import team.gwon.haveameal.common.domain.Token;
import team.gwon.haveameal.common.domain.TokenDto;
import team.gwon.haveameal.member.util.RedisUtil;

@Component
@RequiredArgsConstructor
// @AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TokenProvider {

	private final long ACCESS_TIME = 1000L * 60 * 60;
	private final long REFRESH_TIME = 1000L * 60 * 60 * 24 * 7;
	// public static final String ACCESS_TOKEN = "Access_Token";
	// public static final String REFRESH_TOKEN = "Refresh_Token";
	private static final String BEARER = "Bearer ";
	private final RedisUtil redisUtil;

	@Value("${jwt.secret.key}")
	private String secretKey;
	private Key key;

	// bean으로 등록 되면서 딱 한번 실행이 됩니다.
	@PostConstruct
	public void init() {
		byte[] bytes = Base64.getDecoder().decode(secretKey);
		// secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes(StandardCharsets.UTF_8));
		key = Keys.hmacShaKeyFor(bytes);
	}

	public static Token generateToken(Integer ticketId) {
		// ticketId 를 포함하여 token 생성
		return Token.builder().accessToken(String.valueOf(ticketId)).build();
	}

	//헤더 수정 필요(약속된 키(Authentication)와 type(bearer)으로 수정.
	//하나의 토큰을 받고 클레임값으로 access,refresh 구분하여 검증하는 로직 탈 수 있게 수정.
	// public String getHeaderToken(HttpServletRequest request, String type) {
	// 	return type.equals("Access") ? request.getHeader(ACCESS_TOKEN) : request.getHeader(REFRESH_TOKEN);
	// }

	// // 어세스 토큰 헤더 설정
	// public void setHeaderAccessToken(HttpServletResponse response, String accessToken) {
	// 	response.setHeader(ACCESS_TOKEN, accessToken);
	// }
	//
	// // 리프레시 토큰 헤더 설정
	// public void setHeaderRefreshToken(HttpServletResponse response, String refreshToken) {
	// 	response.setHeader(REFRESH_TOKEN, refreshToken);
	// }
	public void insertRefreshToken(String refreshToken) {
		//redis에 uid로 저장된 트큰이 있는지 확인 후 있으면 삭제 후 삽입 => 업데이트?
		//없으면 바로 삽입.
		Optional<String> flag = redisUtil.getData(getSubject(refreshToken));
		if (flag.isEmpty()) {
			redisUtil.setData(getSubject(refreshToken), refreshToken);
		} else {
			redisUtil.deleteData(getSubject(refreshToken));
			redisUtil.setData(getSubject(refreshToken), refreshToken);
		}
	}

	public String getSubject(String token) {
		return Jwts.parserBuilder()
			.setSigningKey(key)
			.build()
			.parseClaimsJws(token)
			.getBody().getSubject();
	}

	public String getTokenType(String token) {
		return Jwts.parserBuilder()
			.setSigningKey(key)
			.build()
			.parseClaimsJws(token)
			.getBody()
			.get("type", String.class);
	}

	public Optional<String> extractToken(HttpServletRequest request) {
		return Optional.ofNullable(request.getHeader("Authorization")).filter(
			accessToken -> accessToken.startsWith(BEARER)
		).map(accessToken -> accessToken.replace(BEARER, ""));
	}
	//acc인지 ref인지 구분할 수 있는 메소드 구현해야됨.

	public TokenDto createAllToken(String userUid) {
		return new TokenDto(createToken(userUid, "access"), createToken(userUid, "refresh"));
	}

	//access토큰이 만료되면 refresh토큰을 검증하여 재발급해주는 과정에서
	//보안상의 이슈로 refresh토큰도 재발급 하려고 생각중인데 이렇게 되면
	//밑에 코드 각각 생성해주는게 필요할까? 그냥 createAllToken하면 되지 않을까
	// AccessToken 생성
	// public String createAccessToken(String userUid, String userNo, String name) {
	// 	return createToken(userUid, "access");
	// }
	//
	// // RefreshToken 생성
	// public String createRefreshToken(String userUid, String userNo, String name) {
	// 	return createToken(userUid, "refresh");
	// }

	private String createToken(String userUid, String type) {
		Date date = new Date();
		long time = type.equals("access") ? ACCESS_TIME : REFRESH_TIME;
		Claims claims = Jwts.claims().setSubject(userUid);
		claims.put("type", type);
		return Jwts.builder()
			.setClaims(claims)
			.setExpiration(new Date(date.getTime() + time))
			.setIssuedAt(date)
			.signWith(key)
			.compact();
	}

	public boolean tokenValidation(String token) {
		try {
			Jwts.parserBuilder()
				.setSigningKey(key)
				.build()
				.parseClaimsJws(token);
			return true;
		} catch (SecurityException e) {
			throw new JwtException("잘못된 JWT 시그니처");
		} catch (MalformedJwtException e) {
			throw new JwtException("유효하지 않은 JWT 토큰");
		} catch (ExpiredJwtException e) {
			throw new JwtException("토큰 기한 만료");
		} catch (UnsupportedJwtException | IllegalArgumentException e) {
			throw new JwtException("변조된 토큰");
		} catch (Exception e) {
			throw new JwtException("토큰이 비어있음");
		}
		//SecurityException,MalformedJwtException,UnsupportedJwtException,IllegalArgumentException 다 변조된 토큰으로 정의?
	}

	public TokenDto refreshTokenValidation(String token) {
		// DB에 저장한 토큰 비교
		//값이 같으면 acc랑 ref 재생성
		//다르면...?
		// Optional<RefreshToken> refreshToken = refreshTokenRepository.findByAccountNickname(getEmailFromToken(token));
		// return refreshToken.isPresent() && token.equals(refreshToken.get().getRefreshToken());

		// Optional<RefreshToken> refreshToken = 레퍼지토리에 매퍼 만들어서 db에 refreshtoken 꺼내오는 작업 삽입해야함.
		// if(refreshToken.isPresent()) {
		// refreshtoken이 있다면 업데이트
		// }else {
		// 없다면 만들어서 db에 저장.
		// }
		// response.addHeader(TokenProvider.ACCESS_TOKEN, tokenDto.getAccessToken());
		// response.addHeader(TokenProvider.REFRESH_TOKEN, tokenDto.getRefreshToken());

		//토큰 검증을 위한 메소드가 있어야하고, 생성을 위한 메소드 이렇게 구분해야한다.
		//현재 메소드에서는 ref토큰을 이용해 redis의 key(useruid)를 조회해서 값과 매개변수가 일치한다면
		//acc토큰.과 ref토큰을 재생성해줘서 전달
		//일치하지 않는다면 저장된 ref 삭제 후 재로그인 요청.
		//if~~redis에 토큰 uid로 저장된 값과 같은지 확인
		//같으면 createAllToken 호출, insertRefresh 호출해서 값 변경.
		//다르면 redis에 저장된 값 삭제.==> 강제 로그아웃인가? 아님 무엇일까?.--> 삭제시키고 나서 취할 로직이 필요(ex 로그아웃?,예외를 통해서 프론트에서 강제 로그아웃?)
		Optional<String> saveResult = redisUtil.getData(getSubject(token));
		if (saveResult.isPresent()) {
			if (saveResult.get().equals(token)) {
				TokenDto tokenDto = createAllToken(getSubject(token));
				insertRefreshToken(tokenDto.getRefreshToken());
				return tokenDto;
			} else {
				redisUtil.deleteData(getSubject(token));
				throw new JwtException("잘못된 인증으로 로그아웃 요함.");
			}
		} else {
			throw new JwtException("저장된 refresh token이 없음.");
		}
	}
}
