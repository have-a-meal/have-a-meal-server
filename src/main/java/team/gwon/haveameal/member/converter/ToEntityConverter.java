package team.gwon.haveameal.member.converter;

import java.io.IOException;

import org.springframework.stereotype.Component;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import team.gwon.haveameal.member.domain.MemberEntity;
import team.gwon.haveameal.member.domain.MemberRegisterDto;
import team.gwon.haveameal.member.registrationservice.MemberEncryptor;
import team.gwon.haveameal.member.registrationservice.MemberRoleValidator;
import team.gwon.haveameal.member.util.IdGenerator;

@Component
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
public class ToEntityConverter {
	private final MemberEncryptor memberEncryptor;
	private final MemberRoleValidator memberRoleValidator;

	public MemberEntity toMemberEntity(MemberRegisterDto memberRegisterDto) throws IOException {
		MemberRegisterDto encryptedDto = memberEncryptor.encryptMemberData(memberRegisterDto);
		String role = memberRoleValidator.getRole(memberRegisterDto.getMemberId());
		String memberId = memberRegisterDto.getMemberId();
		if (role.equals("외부인")) {
			memberId = IdGenerator.generateId();
		}

		return new MemberEntity(memberId, encryptedDto.getPassword(), encryptedDto.getName(), encryptedDto.getPhone(),
			"ROLE_USER", false, null);
	}
}
