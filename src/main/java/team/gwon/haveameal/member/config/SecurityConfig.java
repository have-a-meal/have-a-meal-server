package team.gwon.haveameal.member.config;

import static org.springframework.security.config.Customizer.*;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.csrf(AbstractHttpConfigurer::disable) // CSRF 보호 비활성화 (테스트용, 실사용 시 적절히 설정 필요)
			.authorizeHttpRequests(authorize -> authorize
				.requestMatchers("/**").permitAll()
			)
			.httpBasic(withDefaults()); // HTTP Basic 인증 사용
		return http.build();
	}
}
