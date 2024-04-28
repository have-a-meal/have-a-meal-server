package team.gwon.haveameal.common.util;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import team.gwon.haveameal.common.domain.Token;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TokenProvider {
	public static Token generateToken() {
		return Token.builder().build();
	}
}
