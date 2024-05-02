package team.gwon.haveameal.common.util;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import team.gwon.haveameal.common.domain.Token;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TokenProvider {
	public static Token generateToken(Integer ticketId) {
		// ticketId 를 포함하여 token 생성
		return Token.builder().accessToken(String.valueOf(ticketId)).build();
	}
}
