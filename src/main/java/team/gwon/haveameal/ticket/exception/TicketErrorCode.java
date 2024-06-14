package team.gwon.haveameal.ticket.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TicketErrorCode {
	TICKET_NOT_FOUND(HttpStatus.BAD_REQUEST, "검색 결과가 없습니다.");

	private final HttpStatus status;
	private final String message;
}