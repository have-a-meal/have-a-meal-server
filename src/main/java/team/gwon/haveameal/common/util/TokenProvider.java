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

	// 테스트를 위해 무조건 true 리턴하게 구현
	public static boolean vaild() {
		return true;
	}
}
