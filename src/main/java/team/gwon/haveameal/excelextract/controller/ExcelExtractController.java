package team.gwon.haveameal.excelextract.controller;

import org.apache.commons.io.FilenameUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import team.gwon.haveameal.common.component.swagger.SwaggerApiBadRequest;
import team.gwon.haveameal.common.component.swagger.SwaggerApiSuccess;
import team.gwon.haveameal.excelextract.component.MenuFacade;
import team.gwon.haveameal.excelextract.error.CustomException;
import team.gwon.haveameal.excelextract.error.ErrorCode;

@RestController
@RequiredArgsConstructor
@RequestMapping("/manager")
public class ExcelExtractController {
	/*
	 마지막 통신 부분에서 구현을 하기 위해 남겨둔 미완성 코드.
	*/
	private final MenuFacade menuFacade;

	@SwaggerApiSuccess(summary = "메뉴 등록", implementation = String.class)
	@SwaggerApiBadRequest
	@PostMapping(value = "/excel")
	public ResponseEntity<String> excelRead(
		@RequestPart("file") MultipartFile multipartFile) throws
		Exception {
		String extension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
		if (!extension.equals("xls") && !extension.equals("xlsx")) {
			throw new CustomException(ErrorCode.NOT_EXCEL_FILE);
		}
		menuFacade.uploadExcel(multipartFile);
		return ResponseEntity.status(HttpStatus.OK).body("success");
	}

	@SwaggerApiSuccess(summary = "메뉴 수정", implementation = String.class)
	@SwaggerApiBadRequest
	@PutMapping("/excel")
	public ResponseEntity<String> excelUpdate(
		@RequestPart("file") MultipartFile multipartFile) throws
		Exception {
		menuFacade.updateAfterDeleteExcel(multipartFile);
		return ResponseEntity.status(HttpStatus.OK).body("success");
	}
}
