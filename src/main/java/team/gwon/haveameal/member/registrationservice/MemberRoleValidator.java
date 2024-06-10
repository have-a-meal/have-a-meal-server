package team.gwon.haveameal.member.registrationservice;

import org.springframework.stereotype.Component;

@Component
public class MemberRoleValidator {
	public String getRole(String idOrEmail) {
		if (isStudent(idOrEmail)) {
			return "학생";
		} else if (isOutsider(idOrEmail)) {
			return "외부인";
		} else {
			throw new IllegalArgumentException("유효하지 않은 ID 또는 이메일입니다.");
		}
	}

	private static boolean isStudent(String idOrEmail) {
		return idOrEmail.matches("^2\\d{7}");
	}

	private static boolean isOutsider(String idOrEmail) {
		return idOrEmail.matches("^[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-z]+$");
	}
}
