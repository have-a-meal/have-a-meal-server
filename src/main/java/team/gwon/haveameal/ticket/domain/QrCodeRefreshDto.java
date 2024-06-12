package team.gwon.haveameal.ticket.domain;

import lombok.Getter;
import team.gwon.haveameal.common.component.TokenCheck;

@Getter
public class QrCodeRefreshDto {

	@TokenCheck
	private String refreshToken;

	private QrCodeRequestDto qrCodeRequestDto;

}
