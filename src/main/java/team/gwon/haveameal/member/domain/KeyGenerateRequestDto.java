package team.gwon.haveameal.member.domain;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class KeyGenerateRequestDto {
	@NotNull(message = "학번은 필수입니다.")
	@Pattern(regexp = "^\\d{8}$", message = "학번은 숫자 8자리입니다.")
	private String memberId;
	@NotNull(message = "키 길이는 필수입니다.")
	private Integer length;

	public static KeyGenerateRequestDto of(String memberId, Integer length) {
		return new KeyGenerateRequestDto(memberId, length);
	}
}
