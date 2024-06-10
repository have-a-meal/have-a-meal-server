package team.gwon.haveameal.member.converter;

import org.springframework.stereotype.Component;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import team.gwon.haveameal.member.domain.MemberEntity;
import team.gwon.haveameal.member.domain.MemberFindDto;
import team.gwon.haveameal.member.registrationservice.MemberEncryptor;

@Component
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
public class ToFindDtoConverter {
	private final MemberEncryptor memberEncryptor;

	public MemberFindDto toMemberFindDto(MemberEntity memberEntity) {
		MemberEntity decodedEntity = memberEncryptor.decryptMemberData(memberEntity);
		return new MemberFindDto(memberEntity.getMemberId(), decodedEntity.getName(), decodedEntity.getPhone());
	}
}
