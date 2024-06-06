package team.gwon.haveameal.ticket.exception;

import lombok.Getter;

@Getter
public class TicketException extends RuntimeException {
	private final TicketErrorCode errorCode;

	public TicketException(TicketErrorCode errorCode) {
		super(errorCode.getMessage());
		this.errorCode = errorCode;
	}
}
