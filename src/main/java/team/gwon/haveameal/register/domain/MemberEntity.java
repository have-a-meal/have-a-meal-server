package team.gwon.haveameal.register.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberEntity {
	private String memberId;
	private String password;
	private String name;
	private String phone;
	private String role;
	private boolean withdrawal;
	private Date withdrawalAt;
}
