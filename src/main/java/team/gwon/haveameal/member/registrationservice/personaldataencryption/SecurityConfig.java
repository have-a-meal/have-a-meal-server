package team.gwon.haveameal.member.registrationservice.personaldataencryption;

import java.security.SecureRandom;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfig {
	@Bean
	public SecureRandom secureRandom() {
		return new SecureRandom();
	}
}
