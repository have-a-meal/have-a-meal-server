package team.gwon.haveameal.member.converter;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import team.gwon.haveameal.member.domain.MemberEntity;
import team.gwon.haveameal.member.domain.MemberRegisterDto;
import team.gwon.haveameal.member.registrationservice.MemberEncryptor;
import team.gwon.haveameal.member.registrationservice.MemberRoleValidator;
import team.gwon.haveameal.member.util.IdGenerator;

@Component
@RequiredArgsConstructor
public class ToEntityConverter {
	private final MemberEncryptor memberEncryptor;
	private final MemberRoleValidator memberRoleValidator;
	private final IdGenerator idGenerator;

	public MemberEntity toMemberEntity(MemberRegisterDto memberRegisterDto) {
		MemberRegisterDto encryptedDto = memberEncryptor.encryptMemberData(memberRegisterDto);
		String role = memberRoleValidator.getRole(memberRegisterDto.getMemberId());
		String memberId = memberRegisterDto.getMemberId();
		if (role.equals("외부인")) {
			memberId = IdGenerator.generateId();
		}

		return new MemberEntity(memberId, encryptedDto.getPassword(), encryptedDto.getName(), encryptedDto.getPhone(),
			role, false, null);
	}
}