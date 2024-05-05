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
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;

		String requestUri = req.getRequestURI();

		log.info("LogFilter doFilter()");
		log.info("---Request(" + requestUri + ") 필터---");
		log.info("data : {}", req.getReader().toString());
		filterChain.doFilter(request, response);
		log.info("---Response(" + requestUri + ") 필터---");

	}

}
