package team.gwon.haveameal.member.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberRegister {
	private String memberId;
	private String password;
	private String name;
	private String phone;
}
