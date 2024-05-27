package team.gwon.haveameal.member.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import team.gwon.haveameal.member.registrationservice.MemberEncryptor;
import team.gwon.haveameal.member.registrationservice.MemberRoleValidator;
import team.gwon.haveameal.member.util.IdGenerator;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
public class MemberRegisterDto {
	private String memberId;
	private String password;
	private String name;
	private String phone;

	private MemberEncryptor memberEncryptor;
	private MemberRoleValidator memberRoleValidator;
	private IdGenerator idGenerator;

	public MemberRegisterDto(String password, String name, String phone) {
		this.password = password;
		this.name = name;
		this.phone = phone;
	}

	public MemberEntity toMemberEntity() {
		MemberRegisterDto encryptedDto = memberEncryptor.encryptMemberData(this);
		String role = memberRoleValidator.getRole(memberId);
		if (role.equals("외부인")) {
			memberId = IdGenerator.generateId();
		}

		return new MemberEntity(memberId, encryptedDto.getPassword(), encryptedDto.getName(), encryptedDto.getPhone(),
			role, false, null);
	}
}
