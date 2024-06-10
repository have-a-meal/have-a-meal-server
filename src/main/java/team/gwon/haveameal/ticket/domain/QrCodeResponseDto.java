package team.gwon.haveameal.ticket.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class QrCodeResponseDto {
	byte[] qrCode;

	public static QrCodeResponseDto from(byte[] qrCode) {
		return new QrCodeResponseDto(qrCode);
	}
}
