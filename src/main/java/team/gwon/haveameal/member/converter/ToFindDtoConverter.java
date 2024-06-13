package team.gwon.haveameal.member.converter;

import org.springframework.stereotype.Component;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import team.gwon.haveameal.member.domain.MemberEntity;
import team.gwon.haveameal.member.domain.MemberFindDto;
import team.gwon.haveameal.member.registrationservice.MemberEncryptor;
import team.gwon.haveameal.member.registrationservice.MemberRoleValidator;

@Component
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
public class ToFindDtoConverter {
	private final MemberEncryptor memberEncryptor;
	private final MemberRoleValidator memberRoleValidator;

	public MemberFindDto toMemberFindDto(MemberEntity memberEntity) {
		MemberEntity decodedEntity = memberEncryptor.decryptMemberData(memberEntity);
		String memberType = memberRoleValidator.getRole(memberEntity.getMemberId());
		return new MemberFindDto(memberEntity.getMemberId(), decodedEntity.getName(), decodedEntity.getPhone(),
			memberType);
	}
}
