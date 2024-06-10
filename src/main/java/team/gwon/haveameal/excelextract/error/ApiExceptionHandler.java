package team.gwon.haveameal.excelextract.error;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {

	@ExceptionHandler(value = CustomException.class)
	public ResponseEntity<String> handlerCustomException(CustomException customException) {
		log.info("[handlerCustomException] {} : {}", customException.getErrorCode().getStatus(),
			customException.getMessage());
		return ResponseEntity.status(customException.getErrorCode().getStatus())
			.body(customException.getErrorCode().getMessage());
	}
}
