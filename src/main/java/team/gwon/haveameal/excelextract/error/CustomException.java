package team.gwon.haveameal.excelextract.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CustomException extends RuntimeException {
	private final ErrorCode errorCode;
}
