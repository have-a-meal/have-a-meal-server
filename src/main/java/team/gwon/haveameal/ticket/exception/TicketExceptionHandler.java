package team.gwon.haveameal.ticket.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TicketExceptionHandler {
	@ExceptionHandler(TicketException.class)
	public ResponseEntity<String> ticketExceptionHandler(TicketException exception) {
		TicketErrorCode errorCode = exception.getErrorCode();
		return ResponseEntity.status(errorCode.getStatus()).body(errorCode.getMessage());
	}
}
