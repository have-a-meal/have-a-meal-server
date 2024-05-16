package team.gwon.haveameal.excelextract.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ErrorResponse {
	private final HttpStatus status;
	private final String message;

	public static ResponseEntity<ErrorResponse> error(CustomException customException) {
		return ResponseEntity.status(customException.getErrorCode().getStatus())
			.body(new ErrorResponse(customException.getErrorCode().getStatus(), customException.getMessage()));
	}
}
