package team.gwon.haveameal.common.domain;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Token {
	private String tokenId;
	private String accessToken;
	private String refreshToken;
}
