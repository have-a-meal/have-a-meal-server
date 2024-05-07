package team.gwon.haveameal.ticket.domain;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class QrCodeUseRequestDto {

	@NotNull
	Long ticketId;
	@NotNull
	Long mealId;

	public TicketEntity toTicketEntity() {
		return TicketEntity.builder().ticketId(ticketId).mealId(mealId).build();
	}

	public static QrCodeUseRequestDto of(Long ticketId, Long mealId) {
		return new QrCodeUseRequestDto(ticketId, mealId);
	}
}
