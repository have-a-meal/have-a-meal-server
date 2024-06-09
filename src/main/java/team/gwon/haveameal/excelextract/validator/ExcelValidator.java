package team.gwon.haveameal.excelextract.validator;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;
import team.gwon.haveameal.excelextract.error.CustomException;
import team.gwon.haveameal.excelextract.error.ErrorCode;

@Slf4j
@Component
public class ExcelValidator implements ConstraintValidator<ExcelCheck, MultipartFile> {
	@Override
	public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext constraintValidatorContext) {
		log.info("ExcelValidator isValid-=---------------------------------");
		String extension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
		if (!extension.equals("xls") && !extension.equals("xlsx")) {
			throw new CustomException(ErrorCode.NOT_EXCEL_FILE);
		}
		// //workbook을 위한 validator를 따로 만들어야하나
		// //이 validator안에서 workbook을 만들어주고 비교 후 처리시키냐 고민..
		// if (workbook.getNumberOfSheets() == 0 || workbook.getSheetAt(0).getPhysicalNumberOfRows() == 0) {
		// 	// throw new IllegalStateException("엑셀 파일에 시트가 존재하지 않습니다.");
		// 	throw new CustomException(ErrorCode.BLANK_EXCEL_FILE);
		// }
		return true;
	}
}
