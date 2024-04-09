package team.gwon.haveameal.unitexcel;

import java.io.FileInputStream;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import team.gwon.haveameal.excelextract.service.ExcelExtractService;

@Slf4j
public class ExcelReadTest {
	public ExcelExtractService excelExtractService;

	@Test
	void testReadExcel() throws Exception {
		excelExtractService = new ExcelExtractService();
		MultipartFile multipartFile = new MockMultipartFile("test.xlsx", new FileInputStream("D:\\4월 1주차 식단표.xlsx"));
		List<Map<String, Object>> testData = excelExtractService.excelUpload(multipartFile);
		// for (Map<String, Object> data : testData) {
		// 	log.info(data.toString());
		// }
	}
}
