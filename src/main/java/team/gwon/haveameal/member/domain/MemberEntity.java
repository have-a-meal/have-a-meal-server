package team.gwon.haveameal.member.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class MemberEntity {
	private String memberId;
	private String password;
	private String name;
	private String phone;
	private String role;
	private boolean withdrawal;
	private Date withdrawalAt;

	public MemberEntity(String name, String phone) {
		this.name = name;
		this.phone = phone;
	}
}
