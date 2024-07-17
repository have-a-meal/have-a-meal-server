package team.gwon.haveameal.common.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class TokenExceptionFilter extends OncePerRequestFilter {
	final Map<String, Object> errorBody = new HashMap<>();

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
		FilterChain filterChain) throws ServletException, IOException {
		try {
			filterChain.doFilter(request, response);
		} catch (JwtException e) {
			setExceptionResponse(request, response, e);
		}
	}

	private void setExceptionResponse(HttpServletRequest request, HttpServletResponse response, JwtException ex) throws
		IOException {
		// response.setStatus(HttpStatus.UNAUTHORIZED.value());
		// response.setContentType("application/json");
		// response.setCharacterEncoding("UTF-8");
		// errorBody.put("errorMessage", ex.getMessage());
		// errorBody.put("path", request.getRequestURI());
		// response.getWriter().write(new ObjectMapper().writeValueAsString(errorBody));
		response.setContentType("application/json;charset=UTF-8");
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.getWriter().print(ex.getMessage());
	}
}
