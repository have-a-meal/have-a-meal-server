package team.gwon.haveameal.common.domain;

import org.springframework.data.redis.core.RedisHash;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@RedisHash("token")
public class Token {
	private String tokenId;
	private String accessToken;
	private String refreshToken;
}
