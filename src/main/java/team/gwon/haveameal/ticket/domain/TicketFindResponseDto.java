package team.gwon.haveameal.ticket.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TicketFindResponseDto {
	private String paymentId;
	private String timing;
	private String courseType;
	private int price;

	public static TicketFindResponseDto of(String paymentId, String timing, String courseType, int price) {
		return new TicketFindResponseDto(paymentId, timing, courseType, price);
	}
}
