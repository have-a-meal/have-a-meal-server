package team.gwon.haveameal.excelextract.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import team.gwon.haveameal.excelextract.component.MenuFacade;

@RestController
@RequiredArgsConstructor
@RequestMapping("/manager")
public class ExcelExtractController {
	/*
	 마지막 통신 부분에서 구현을 하기 위해 남겨둔 미완성 코드.
	*/
	private final MenuFacade menuFacade;

	@PostMapping("/excel")
	public ResponseEntity<String> excelRead(@RequestPart(value = "file") MultipartFile multipartFile) throws Exception {
		menuFacade.uploadExcel(multipartFile);
		return ResponseEntity.status(HttpStatus.OK).body("success");
	}

	@PutMapping("/excel")
	public ResponseEntity<String> excelUpdate(@RequestPart(value = "file") MultipartFile multipartFile) throws
		Exception {
		menuFacade.updateAfterDeleteExcel(multipartFile);
		return ResponseEntity.status(HttpStatus.OK).body("success");
	}
}
