package team.gwon.haveameal.member.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import team.gwon.haveameal.member.encryptionservice.MemberEncryptor;

@Getter
@RequiredArgsConstructor
public class MemberRegisterDto {
	private String memberId;
	private String password;
	private String name;
	private String phone;

	private MemberEncryptor memberEncryptor;

	public MemberRegisterDto(String password, String name, String phone) {
		this.password = password;
		this.name = name;
		this.phone = phone;
	}

	public MemberEntity toMemberEntity() {
		MemberRegisterDto encryptedDto = memberEncryptor.encryptMemberData(this);
		/* 학생 직원 외부인 구분 필요
		외부인 ID 생성 로직 필요
		탈퇴 여부, 탈퇴 날짜? */
		return new MemberEntity(memberId, encryptedDto.getPassword(), encryptedDto.getName(), encryptedDto.getPhone(),
			"학생", false, null);
	}
}
