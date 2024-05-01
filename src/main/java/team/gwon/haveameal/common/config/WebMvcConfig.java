package team.gwon.haveameal.common.config;

import java.util.Arrays;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import team.gwon.haveameal.common.logging.LogFilter;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	// filter에 포함되는 URL의 주소입니다.
	private static final String[] INCLUDE_PATHS = {

	};

	@Bean
	public FilterRegistrationBean filterBean() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean(new LogFilter());
		// 필터 여러개 적용 시에는 순번이 있어야합니다.
		// LogFilter가 동작하는 URL을 설정해줘야합니다.
		registrationBean.setUrlPatterns(Arrays.asList(
			"/test/*", "/insert/*"
		));

		return registrationBean;
	}
}
