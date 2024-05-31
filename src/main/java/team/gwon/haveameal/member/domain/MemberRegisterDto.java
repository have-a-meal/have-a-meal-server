package team.gwon.haveameal.member.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
public class MemberRegisterDto {
	private String memberId;
	private String password;
	private String name;
	private String phone;

	public MemberRegisterDto(String password, String name, String phone) {
		this.password = password;
		this.name = name;
		this.phone = phone;
	}
}
