package team.gwon.haveameal.ticket.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketEntity {
	private Long ticketId;
	private byte[] paymentId;
	private Long mealId;
}
