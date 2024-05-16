package team.gwon.haveameal.excelextract.error;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {

	@ExceptionHandler(value = CustomException.class)
	public ResponseEntity<ErrorResponse> handlerCustomException(CustomException customException) {
		log.info("[handlerCustomException] {} : {}", customException.getErrorCode().getStatus(),
			customException.getMessage());
		return ErrorResponse.error(customException);
	}
}
