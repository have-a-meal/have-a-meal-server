package team.gwon.haveameal.member.util;

import java.time.Duration;
import java.util.Optional;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RedisUtil {
	private final StringRedisTemplate stringRedisTemplate;

	public Optional<String> getData(String key) {
		ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
		String nullCheck = valueOperations.get(key);
		return Optional.ofNullable(nullCheck);
	}

	public void setData(String key, String value) {
		ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
		valueOperations.set(key, value);
	}

	public void setDataExpire(String key, String value, long duration) {
		ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
		Duration expireDuration = Duration.ofSeconds(duration);
		valueOperations.set(key, value, expireDuration);
	}

	public void deleteData(String key) {
		stringRedisTemplate.delete(key);
	}
}
