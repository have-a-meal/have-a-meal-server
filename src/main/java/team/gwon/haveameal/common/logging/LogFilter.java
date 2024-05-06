package team.gwon.haveameal.common.logging;

import java.io.IOException;

import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
		FilterChain filterChain) throws ServletException, IOException {
		CachedHttpServletRequest cachedReq = new CachedHttpServletRequest((HttpServletRequest)request);
		HttpServletResponse res = (HttpServletResponse)response;

		String requestUri = cachedReq.getRequestURI();
		String requestMethod = cachedReq.getMethod();
		String requestBody = cachedReq.getCachedRequestData();

		log.info("LogFilter doFilter()");
		log.info("---Method({}), Request({}) 필터---", requestMethod, requestUri);
		log.info("requestBodyData : \n{}", requestBody);
		filterChain.doFilter(cachedReq, res);
		log.info("---Method({}), Request({}) 필터---", requestMethod, requestUri);

	}

}
