package team.gwon.haveameal.member.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import team.gwon.haveameal.member.registrationservice.MemberEncryptor;

@Getter
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class MemberFindDto {
	private String memberId;
	private String name;
	private String phone;

	private static MemberEncryptor memberEncryptor;

	public static MemberFindDto toMemberFindDto(MemberEntity memberEntity) {
		MemberEntity decodedEntity = memberEncryptor.decryptMemberData(memberEntity);
		return new MemberFindDto(memberEntity.getMemberId(), decodedEntity.getName(), decodedEntity.getPhone());
	}
}
