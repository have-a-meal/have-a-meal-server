package team.gwon.haveameal.ticket.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class QrCodeUseResponseDto {
	private String message;

	public static QrCodeUseResponseDto from(String message) {
		return new QrCodeUseResponseDto(message);
	}
}
