package team.gwon.haveameal.member.domain;

import java.util.Date;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import team.gwon.haveameal.member.encryptionservice.password.PasswordEncryptor;
import team.gwon.haveameal.member.encryptionservice.personaldata.PersonalDataEncryptor;

@Getter
@RequiredArgsConstructor
public class MemberRegisterDto {
	private String memberId;
	private String password;
	private String name;
	private String phone;

	private final PersonalDataEncryptor personalDataEncryptor;
	private final PasswordEncryptor passwordEncryptor;

	public MemberEntity toMemberEntity() {
		String encryptedId = personalDataEncryptor.encryptData(memberId);
		String encryptedPassword = passwordEncryptor.encryptPassword(password);
		String encryptedName = personalDataEncryptor.encryptData(name);
		String encryptedPhone = personalDataEncryptor.encryptData(phone);
		String role = "학생";
		boolean withdrawal = false;
		Date withdrawalAt = null;
		/* 학생 직원 외부인 구분 필요
		외부인 ID 생성 로직 필요
		탈퇴 여부, 탈퇴 날짜? */
		return new MemberEntity(encryptedId, encryptedPassword, encryptedName, encryptedPhone, role,
			withdrawal, withdrawalAt);
	}
}
