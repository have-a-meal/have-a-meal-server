package team.gwon.haveameal.unitexcel;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileInputStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

import lombok.extern.slf4j.Slf4j;
import team.gwon.haveameal.excelextract.component.MenuFacade;
import team.gwon.haveameal.excelextract.service.ExcelExtractService;

@SpringBootTest
@Slf4j
public class ExcelIntoDbTest {

	@Autowired
	private ExcelExtractService excelExtractService;

	@Autowired
	private MenuFacade menuFacade;

	@Test
	public void dataIntoDB() throws Exception {
		String fileName = "april week 1 menu";
		String contentType = "xlsx";
		String filePath = "src/test/resources/excel/april week 1 menu.xlsx";
		MockMultipartFile multipartFile = new MockMultipartFile(fileName, fileName + "." + contentType, contentType,
			new FileInputStream(filePath));
		excelExtractService.excelUpload(multipartFile);
	}

	@Test
	public void dataIntoDB_Facade() throws Exception {
		String fileName = "april week 1 menu";
		String contentType = "xlsx";
		String filePath = "src/test/resources/excel/april week 1 menu.xlsx";
		MockMultipartFile multipartFile = new MockMultipartFile(fileName, fileName + "." + contentType, contentType,
			new FileInputStream(filePath));
		menuFacade.uploadExcel(multipartFile);
	}

	@Test
	public void dataIntoDB_duplicate_test() throws Exception {
		String fileName = "april week 1 menu";
		String contentType = "xlsx";
		String filePath = "src/test/resources/excel/april week 1 menu.xlsx";
		MockMultipartFile multipartFile = new MockMultipartFile(fileName, fileName + "." + contentType, contentType,
			new FileInputStream(filePath));
		menuFacade.uploadExcel(multipartFile);
		Throwable exception = assertThrows(RuntimeException.class, () -> {
			menuFacade.uploadExcel(multipartFile);
		});
		assertEquals("duplicate excel data", exception.getMessage());
	}
}
