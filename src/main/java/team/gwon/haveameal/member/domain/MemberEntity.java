package team.gwon.haveameal.member.domain;

import java.util.Date;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MemberEntity {
	private String memberId;
	private String password;
	private String name;
	private String phone;
	private String role;
	private boolean withdrawal;
	private Date withdrawalAt;
}
