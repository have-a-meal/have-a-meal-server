package team.gwon.haveameal.excelextract.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import team.gwon.haveameal.excelextract.service.ExcelExtractService;

@RestController
@RequiredArgsConstructor
public class ExcelExtractController {

	private final ExcelExtractService excelExtractService;

	@PostMapping("/upload")
	public void excelRead(@RequestPart(value = "file", required = false) MultipartFile multipartFile) throws Exception {
		excelExtractService.excelUpload(multipartFile);
	}
}
