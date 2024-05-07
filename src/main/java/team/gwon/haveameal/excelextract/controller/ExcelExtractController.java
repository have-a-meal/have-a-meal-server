package team.gwon.haveameal.excelextract.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import team.gwon.haveameal.excelextract.component.MenuFacade;

@RestController
@RequiredArgsConstructor
public class ExcelExtractController {
	/*
	 마지막 통신 부분에서 구현을 하기 위해 남겨둔 미완성 코드.
	*/
	private final MenuFacade menuFacade;

	@PostMapping("/upload")
	public void excelRead(@RequestPart(value = "file", required = false) MultipartFile multipartFile) throws Exception {
		menuFacade.uploadExcel(multipartFile);
	}
}
