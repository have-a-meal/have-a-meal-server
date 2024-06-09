package team.gwon.haveameal.unitexcel;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileInputStream;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import team.gwon.haveameal.excelextract.component.MenuFacade;
import team.gwon.haveameal.excelextract.error.CustomException;
import team.gwon.haveameal.excelextract.service.ExcelExtractService;

@Slf4j
@SpringBootTest
@Transactional
public class ExcelReadTest {

	@Autowired
	private ExcelExtractService excelExtractService;

	@Autowired
	private MenuFacade menuFacade;

	@Test
	void testReadExcel() throws Exception {
		String fileName = "april week 1 menu";
		String contentType = "xlsx";
		String filePath = "src/test/resources/excel/april week 1 menu.xlsx";
		MockMultipartFile multipartFile = new MockMultipartFile(fileName, fileName + "." + contentType, contentType,
			new FileInputStream(filePath));
		List<Map<String, Object>> testData = excelExtractService.excelUpload(multipartFile);
		for (Map<String, Object> data : testData) {
			log.info(data.toString());
		}
	}

	// @Test
	// void testExcelExtension() throws Exception {
	// 	String fileName = "testtextfile";
	// 	String contentType = "txt";
	// 	String filePath = "src/test/resources/text/testtextfile.txt";
	// 	MockMultipartFile multipartFile = new MockMultipartFile(fileName, fileName + "." + contentType, contentType,
	// 		new FileInputStream(filePath));
	// 	CustomException exception = assertThrows(CustomException.class, () -> {
	// 		menuFacade.uploadExcel(multipartFile);
	// 	});
	// 	assertEquals("엑셀 파일이 아닙니다.", exception.getMessage());
	// 	// Assertions.assertThrows(CustomException.class, () -> {
	// 	// 	menuFacade.uploadExcel(multipartFile);
	// 	// });
	// }

	@Test
	void testBlankExcel() throws Exception {
		String fileName = "blankexcel";
		String contentType = "xlsx";
		String filePath = "src/test/resources/excel/blankexcel.xlsx";
		MockMultipartFile multipartFile = new MockMultipartFile(fileName, fileName + "." + contentType, contentType,
			new FileInputStream(filePath));
		Throwable exception = assertThrows(CustomException.class, () -> {
			menuFacade.uploadExcel(multipartFile);
		});
		assertEquals("빈 엑셀 파일입니다.", exception.getMessage());
	}
}
