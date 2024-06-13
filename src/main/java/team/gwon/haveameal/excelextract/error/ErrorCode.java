package team.gwon.haveameal.excelextract.error;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
	//== 200 ==//
	SUCCESS(HttpStatus.OK, "success"),

	//== 400 ==//
	INCORRECT_DATE_FORMAT(HttpStatus.BAD_REQUEST, "날짜 형식 맞지 않습니다."),
	INCORRECT_COURSE_ARGUMENT(HttpStatus.BAD_REQUEST, "존재하지 않는 코스 입니다."),
	NOT_VALID_EXCEL_DATA(HttpStatus.BAD_REQUEST, "제공된 템플릿 형식에 맞게 다시 작성 부탁 드립니다."),
	NOT_EXCEL_FILE(HttpStatus.BAD_REQUEST, "엑셀 파일이 아닙니다."),
	BLANK_EXCEL_FILE(HttpStatus.BAD_REQUEST, "빈 엑셀 파일입니다."),
	DUPLICATED_EXCEL_FILE(HttpStatus.BAD_REQUEST, "올린 기록이 있는 엑셀 파일입니다."),
	EMPTY_DATE(HttpStatus.NOT_FOUND, "요청데이터에 날짜가 포함되어 있지 않습니다."),
	FAILED_INSERT(HttpStatus.BAD_REQUEST, "엑셀파일 업로드가 실패하였습니다."),
	FAILED_DELETE(HttpStatus.BAD_REQUEST, "엑셀파일 수정에 실패하였습니다.");
	private final HttpStatus status;
	private final String message;

}
