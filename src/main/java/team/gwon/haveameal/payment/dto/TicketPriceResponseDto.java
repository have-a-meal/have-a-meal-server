package team.gwon.haveameal.payment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import team.gwon.haveameal.payment.entity.TicketPrice;

@Getter
@AllArgsConstructor
@ToString
public class TicketPriceResponseDto {
	int courseId;
	int price;

	public static TicketPriceResponseDto fromTicketPrice(TicketPrice ticketPrice) {
		return new TicketPriceResponseDto(ticketPrice.getCourseId(), ticketPrice.getPrice());
	}
}
