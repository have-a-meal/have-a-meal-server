package team.gwon.haveameal.member.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class LoginRequestDto {
	private String memberId;
	private String password;
}
