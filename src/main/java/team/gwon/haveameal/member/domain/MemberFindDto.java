package team.gwon.haveameal.member.domain;

import lombok.Getter;

@Getter
public class MemberFindDto {
	private String memberId;
	private String password;
	private String name;
	private String phone;
}
