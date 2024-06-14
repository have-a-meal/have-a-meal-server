package team.gwon.haveameal.ticket.domain;

import lombok.Getter;

@Getter
public class TicketQuantityRequestDto {
	private String courseType;
	private String timing;
	private String memberId;
}
