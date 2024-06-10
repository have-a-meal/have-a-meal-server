package team.gwon.haveameal.member.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class EmailConfig {

	@Bean
	public JavaMailSender javaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");    // 이메일 전송에 사용할 SMTP 서버 호스트
		mailSender.setPort(587);
		mailSender.setUsername("haveameal99@gmail.com");
		mailSender.setPassword("whsd hcse rwnv uqti");

		Properties javaMailProperties = new Properties();
		javaMailProperties.put("mail.transport.protocol", "smtp");
		javaMailProperties.put("mail.smtp.auth", "true");
		javaMailProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		javaMailProperties.put("mail.smtp.starttls.enable", "true");
		javaMailProperties.put("mail.debug", "true");
		javaMailProperties.put("mail.smtp.ssl.trust", "smtp.naver.com");
		javaMailProperties.put("mail.smtp.ssl.protocols", "TLSv1.2");

		mailSender.setJavaMailProperties(javaMailProperties);

		return mailSender;
	}
}
