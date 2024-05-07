package team.gwon.haveameal.member.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberFindDto {
	private String memberId;
	private String password;
	private String name;
	private String phone;

	public static MemberFindDto toMemberFindDto(MemberEntity memberEntity) {
		return new MemberFindDto(memberEntity.getMemberId(), memberEntity.getPassword(),
			memberEntity.getName(), memberEntity.getPhone());
	}
}
