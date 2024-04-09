package team.gwon.haveameal.unitexcel;

import java.io.FileInputStream;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;

import lombok.extern.slf4j.Slf4j;
import team.gwon.haveameal.excelextract.component.ExtractData;

@Slf4j
public class ExcelReadTest {

	@Test
	void testReadExcel() throws Exception {
		String fileName = "april week 1 menu";
		String contentType = "xlsx";
		String filePath = "src/test/resources/excel/april week 1 menu.xlsx";
		MockMultipartFile multipartFile = new MockMultipartFile(fileName, fileName + "." + contentType, contentType,
			new FileInputStream(filePath));
		List<Map<String, Object>> testData = ExtractData.extract(multipartFile);
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
		ExtractData.extract(multipartFile);
	}

	@Test
	void testBlankExcel() throws Exception {
		String fileName = "blankexcel";
		String contentType = "xlsx";
		String filePath = "src/test/resources/excel/blankexcel.xlsx";
		MockMultipartFile multipartFile = new MockMultipartFile(fileName, fileName + "." + contentType, contentType,
			new FileInputStream(filePath));
		ExtractData.extract(multipartFile);
	}
}
