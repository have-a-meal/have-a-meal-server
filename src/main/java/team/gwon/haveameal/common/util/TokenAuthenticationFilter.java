package team.gwon.haveameal.common.util;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Component
@Slf4j
public class TokenAuthenticationFilter extends OncePerRequestFilter {

	private final TokenProvider tokenProvider;

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) {
		String[] excludePath = {"/members/", "/members/login", "/members/email", "/members/emailCheck"};
		String path = request.getRequestURI();
		return Arrays.stream(excludePath).anyMatch(path::startsWith);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
		FilterChain filterChain) throws ServletException, IOException {
		//요청헤더에 들어오는 토큰 추출.
		//토큰 검증 로직 구현 해야함.
		// List<String> list = Arrays.asList(
		// 	"/members/",  // 로그인 페이지의 URL을 추가합니다.
		// 	"/members/*"
		// );
		//
		// // 2. 토큰이 필요하지 않은 API URL의 경우 -> 로직 처리없이 다음 필터로 이동한다.
		// if (list.contains(request.getRequestURI())) {
		// 	filterChain.doFilter(request, response);
		// 	return;
		// }
		// log.info("doFilterInternal");
		// if (shouldNotFilter(request)) {
		// 	filterChain.doFilter(request, response);
		// }
		// Optional<String> token = tokenProvider.extractToken(request);
		// try {
		// 	if (token.isPresent() && tokenProvider.tokenValidation(token.get())) {
		// 		if (tokenProvider.getTokenType(token.get()).equals("refresh")) {
		// 			log.info("ref-token: {}", token.get());
		// 			//refresh 검증 먼저해줘야함.
		// 			TokenDto tokenDto = tokenProvider.refreshTokenValidation(token.get());
		// 			response.addHeader("Authorization", "Bearer " + tokenDto.getAccessToken());
		// 			response.addHeader("Refresh-Token", "Bearer " + tokenDto.getRefreshToken());
		// 		} else {
		// 			log.info("acc-token: {}", token.get());
		// 		}
		// 		// filterChain.doFilter(request, response);
		// 	} else {
		// 		throw new JwtException("토큰 값이 없습니다.");
		// 		// log.info("토큰이 없습니다.");
		// 	}
		// } catch (JwtException e) {
		// 	log.error(e.getMessage(), e);
		// 	response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		// 	response.setContentType(APPLICATION_JSON_VALUE);
		// 	response.setCharacterEncoding("UTF-8");
		// 	response.getWriter().write(e.getMessage());
		// 	return;
		// }
		// filterChain.doFilter(request, response);
		//=====================================================
		Optional<String> token = tokenProvider.extractToken(request);
		log.info("[doFilterInternal] token 값 추출 완료. token : {}", token);

		log.info("[doFilterInternal] token 값 유효성 체크 시작");
		if (token.isPresent() && tokenProvider.tokenValidation(token.get())) {
			Authentication authentication = tokenProvider.getAuthentication(token.get());
			SecurityContextHolder.getContext().setAuthentication(authentication);
			log.info("[doFilterInternal] token 값 유효성 체크 완료");
		} else {
			log.info("[doFilterInternal] token 값 유효성 체크 실패");
		}

		filterChain.doFilter(request, response);
	}
}
