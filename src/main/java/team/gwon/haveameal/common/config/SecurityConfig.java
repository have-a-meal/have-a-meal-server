package team.gwon.haveameal.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;
import team.gwon.haveameal.common.util.TokenAuthenticationFilter;
import team.gwon.haveameal.common.util.TokenProvider;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
	private final TokenProvider tokenProvider;

	// private TokenAuthenticationFilter tokenAuthenticationFilter(TokenProvider tokenProvider) {
	// 	return new TokenAuthenticationFilter(tokenProvider);
	// }
	//
	// private Filter tokenExceptionFilter() {
	// 	return new TokenExceptionFilter();
	// }
	// @Bean
	// public WebSecurityCustomizer webSecurityCustomizer() {
	// 	return (web) -> web.ignoring().requestMatchers(new AntPathRequestMatcher("/members/email"));
	// }
	// private final TokenExceptionFilter tokenExceptionFilter;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.csrf(AbstractHttpConfigurer::disable)
			.authorizeHttpRequests(
				authorize -> authorize
					.requestMatchers("/members/**", "/error").permitAll()
					// .anyRequest().hasRole("USER")
					.anyRequest().authenticated()
			).addFilterBefore(new TokenAuthenticationFilter(tokenProvider), UsernamePasswordAuthenticationFilter.class)
			.sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		// .addFilterBefore(tokenExceptionFilter, TokenAuthenticationFilter.class);
		return http.build();
	}

}
