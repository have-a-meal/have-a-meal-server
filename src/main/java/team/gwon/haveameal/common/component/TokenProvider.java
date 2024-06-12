package team.gwon.haveameal.common.component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import io.jsonwebtoken.security.WeakKeyException;
import lombok.extern.slf4j.Slf4j;
import team.gwon.haveameal.common.domain.Token;

@Slf4j
@Component
public class TokenProvider {

	private final String secretKey;

	public TokenProvider(@Value("${spring.jwt.secret}") String secretKey) {
		this.secretKey = secretKey;
	}

	public Token createToken(String memberId) {
		String access_token = Jwts.builder()
			.setHeader(createHeader())
			.setClaims(createClaims())
			.setSubject(memberId)
			.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 3)) // 토큰 만료 시간
			.signWith(createSignature(), SignatureAlgorithm.HS256)
			.compact();

		String refresh_token = Jwts.builder()
			.setSubject(memberId)
			.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
			.signWith(createSignature(), SignatureAlgorithm.HS256)
			.compact();

		return Token.builder().accessToken(access_token).refreshToken(refresh_token).build();
	}

	public Claims getClaims(String token) {
		return Jwts.parserBuilder()
			.setSigningKey(createSignature())
			.build()
			.parseClaimsJws(token)
			.getBody();
	}

	public boolean isValidToken(String token) {
		try {
			Claims claims = getClaims(token);
			return true;
		} catch (ExpiredJwtException exception) {
			log.error("Token Expired");
			throw new ExpiredJwtException(exception.getHeader(), exception.getClaims(), token);
		} catch (SignatureException exception) {
			log.error("Token Tampered");
			throw new SignatureException("유효하지 않은 JWT 서명입니다.");
		} catch (UnsupportedJwtException | WeakKeyException exception) {
			log.error("Unsupported Token");
			throw new UnsupportedJwtException("지원되지 않는 토큰입니다.");
		} catch (MalformedJwtException | IllegalArgumentException exception) {
			throw new MalformedJwtException("잘못된 형식의 토큰입니다.");
		}
	}

	private Map<String, Object> createHeader() {
		Map<String, Object> headers = new HashMap<>();

		headers.put("typ", "JWT");
		headers.put("alg", "HS256"); // 서명 생성에 사용될 알고리즘

		return headers;
	}

	// Claim -> 사용자 혹은 토큰에 대한 property 를 key-value 형태로 저장함.
	private Map<String, Object> createClaims() {
		Map<String, Object> claims = new HashMap<>();

		claims.put("iat", System.currentTimeMillis()); // 토큰 발급 시간
		claims.put("token_type", "Bearer");
		return claims;
	}

	private Key createSignature() {
		byte[] apiKeySecretBytes = secretKey.getBytes(StandardCharsets.UTF_8);
		return new SecretKeySpec(apiKeySecretBytes, SignatureAlgorithm.HS256.getJcaName());
	}
}
