package team.gwon.haveameal.ticket.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TicketQuantityResponseDto {
	int quantity;

	public static TicketQuantityResponseDto from(int quantity) {
		return new TicketQuantityResponseDto(quantity);
	}
}
