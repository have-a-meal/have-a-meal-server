package team.gwon.haveameal.member.domain;

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
		MemberEntity member = new MemberEntity();
		member.setMemberId(personalDataEncryptor.encryptData(memberId));
		member.setPassword(passwordEncryptor.encryptPassword(password));
		member.setName(personalDataEncryptor.encryptData(name));
		member.setPhone(personalDataEncryptor.encryptData(phone));
		// 학생 직원 외부인 구분 필요
		// 탈퇴 여부, 탈퇴 날짜?
		return member;
	}
}
