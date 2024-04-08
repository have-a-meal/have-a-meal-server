package team.gwon.haveameal.domain;

import java.util.Date;

import lombok.Data;

@Data
public class Member {
	private String member_id;
	private String password;
	private String name;
	private String phone;
	private String role;
	private boolean withdrawal;
	private Date withdrawal_at;
}
