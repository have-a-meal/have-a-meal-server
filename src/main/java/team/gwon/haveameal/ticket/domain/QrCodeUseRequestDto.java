package team.gwon.haveameal.ticket.domain;

import lombok.Getter;
import team.gwon.haveameal.common.component.TokenCheck;

@Getter
public class QrCodeUseRequestDto {
	@TokenCheck
	private String accessToken;

	private Long mealId;

}
