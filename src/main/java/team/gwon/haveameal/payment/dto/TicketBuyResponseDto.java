package team.gwon.haveameal.payment.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TicketBuyResponseDto {
	private byte[] paymentId;
}
