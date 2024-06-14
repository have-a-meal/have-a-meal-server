package team.gwon.haveameal.member.service;

import java.util.Random;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import team.gwon.haveameal.member.registrationservice.MemberRoleValidator;
import team.gwon.haveameal.member.util.RedisUtil;

@Service
@RequiredArgsConstructor
public class MailService {
	private final JavaMailSender mailSender;
	private final RedisUtil redisUtil;
	private final MemberRoleValidator memberRoleValidator;
	private int authNumber;

	public void makeRandomNumber() {
		Random rand = new Random();
		StringBuilder randomNumber = new StringBuilder();
		for (int i = 0; i < 6; i++) {
			randomNumber.append(rand.nextInt(10));
		}

		authNumber = Integer.parseInt(randomNumber.toString());
	}

	public String joinEmail(String idOrEmail) {
		makeRandomNumber();
		String setFrom = "haveameal99@gmail.com";
		String title = "회원 가입 인증 이메일 입니다.";
		String content =
			"인증 번호는 " + authNumber + " 입니다.";
		String role = memberRoleValidator.getRole(idOrEmail);
		if (role.equals("학생")) {
			idOrEmail += "@st.yc.ac.kr";
		}
		mailSend(setFrom, idOrEmail, title, content);
		return Integer.toString(authNumber);
	}

	public void mailSend(String setFrom, String toMail, String title, String content) {
		MimeMessage message = mailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
			helper.setFrom(setFrom);
			helper.setTo(toMail);
			helper.setSubject(title);
			helper.setText(content, true);
			mailSender.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		redisUtil.setDataExpire(toMail, Integer.toString(authNumber), 60 * 5L);
	}

	public boolean checkAuthNum(String email, String authNum) {
		if (redisUtil.getData(email) == null) {
			return false;
		} else if (redisUtil.getData(email).equals(authNum)) {
			return true;
		} else {
			return false;
		}
	}
}
