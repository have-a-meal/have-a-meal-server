package team.gwon.haveameal.ticket.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TicketEntity {
	private byte[] ticketId;
	private byte[] paymentId;
	private Long mealId;
}
