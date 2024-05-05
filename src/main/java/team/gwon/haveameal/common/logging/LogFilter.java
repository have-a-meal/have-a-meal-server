package team.gwon.haveameal.common.logging;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogFilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.info("LogFilter init()");
		Filter.super.init(filterConfig);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws
		IOException,
		ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;

		String requestUri = req.getRequestURI();

		log.info("LogFilter doFilter()");
		log.info("---Request(" + requestUri + ") 필터---");
		log.info("data : {}", req.getReader().toString());
		chain.doFilter(request, response);
		log.info("---Response(" + requestUri + ") 필터---");

	}

	@Override
	public void destroy() {
		log.info("LogFilter destroy()");
		Filter.super.destroy();
	}
}
