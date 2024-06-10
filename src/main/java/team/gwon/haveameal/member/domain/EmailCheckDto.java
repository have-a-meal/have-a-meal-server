package team.gwon.haveameal.member.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class EmailCheckDto {
	private String email;
	private String authNum;
}
