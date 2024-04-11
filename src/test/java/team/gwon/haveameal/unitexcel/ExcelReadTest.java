package team.gwon.haveameal.unitexcel;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

import lombok.extern.slf4j.Slf4j;
import team.gwon.haveameal.excelextract.service.ExcelExtractService;

@Slf4j
@SpringBootTest
public class ExcelReadTest {

	@Autowired
	private ExcelExtractService excelExtractService;

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

	@Test
	void testExcelExtension() throws Exception {
		String fileName = "testtextfile";
		String contentType = "txt";
		String filePath = "src/test/resources/text/testtextfile.txt";
		MockMultipartFile multipartFile = new MockMultipartFile(fileName, fileName + "." + contentType, contentType,
			new FileInputStream(filePath));
		Throwable exception = assertThrows(IOException.class, () -> {
			excelExtractService.excelUpload(multipartFile);
		});
		assertEquals("엑셀 파일이 아닙니다.", exception.getMessage());
	}

	@Test
	void testBlankExcel() throws Exception {
		String fileName = "blankexcel";
		String contentType = "xlsx";
		String filePath = "src/test/resources/excel/blankexcel.xlsx";
		MockMultipartFile multipartFile = new MockMultipartFile(fileName, fileName + "." + contentType, contentType,
			new FileInputStream(filePath));
		Throwable exception = assertThrows(IllegalStateException.class, () -> {
			excelExtractService.excelUpload(multipartFile);
		});
		assertEquals("엑셀 파일에 시트가 존재하지 않습니다.", exception.getMessage());
	}
}
