package team.gwon.haveameal.excelextract.error;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
	//== 200 ==//
	SUCCESS(HttpStatus.OK, "OK"),

	//== 400 ==//
	// NOT_SUPPORTED_HTTP_METHOD(HttpStatus.BAD_REQUEST, "지원하지 않는 Http Method 방식입니다."),
	// NOT_VALID_METHOD_ARGUMENT(HttpStatus.BAD_REQUEST, "유효하지 않은 Request Body 혹은 Argument입니다."),
	// USER_NOT_FOUND(HttpStatus.BAD_REQUEST, "해당 사용자를 찾을 수 없습니다."),
	// ITEM_NOT_FOUND(HttpStatus.BAD_REQUEST, "해당 상품을 찾을 수 없습니다.");
	INCORRECT_DATE_FORMAT(HttpStatus.BAD_REQUEST, "날짜 형식 맞지 않습니다."),
	INCORRECT_COURSE_ARGUMENT(HttpStatus.BAD_REQUEST, "존재하지 않는 코스 입니다."),
	NOT_VALID_EXCEL_DATA(HttpStatus.BAD_REQUEST, "제공된 템플릿 형식에 맞게 다시 작성 부탁 드립니다."),
	NOT_EXCEL_FILE(HttpStatus.BAD_REQUEST, "엑셀 파일이 아닙니다."),
	BLANK_EXCEL_FILE(HttpStatus.BAD_REQUEST, "빈 엑셀 파일입니다."),
	DUPLICATED_EXCEL_FILE(HttpStatus.BAD_REQUEST, "올린 기록이 있는 엑셀 파일입니다.");

	private final HttpStatus status;
	private final String message;

}
