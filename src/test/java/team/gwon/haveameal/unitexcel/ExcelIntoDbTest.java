package team.gwon.haveameal.unitexcel;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileInputStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

import lombok.extern.slf4j.Slf4j;
import team.gwon.haveameal.excelextract.component.MenuFacade;
import team.gwon.haveameal.excelextract.error.CustomException;
import team.gwon.haveameal.excelextract.service.DayMenuService;
import team.gwon.haveameal.excelextract.service.ExcelExtractService;

@SpringBootTest
@Slf4j
public class ExcelIntoDbTest {

	@Autowired
	private ExcelExtractService excelExtractService;

	@Autowired
	private MenuFacade menuFacade;
	@Autowired
	private DayMenuService dayMenuService;

	// @Test
	// public void dataIntoDB() throws Exception {
	// 	String fileName = "april week 1 menu";
	// 	String contentType = "xlsx";
	// 	String filePath = "src/test/resources/excel/april week 1 menu.xlsx";
	// 	MockMultipartFile multipartFile = new MockMultipartFile(fileName, fileName + "." + contentType, contentType,
	// 		new FileInputStream(filePath));
	// 	excelExtractService.excelUpload(multipartFile);
	// }

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
	public void dataIntoDB_Facade_dateValid() throws Exception {
		String fileName = "april week 1 dateerrormenu";
		String contentType = "xlsx";
		String filePath = "src/test/resources/excel/april week 1 dateerrormenu.xlsx";
		MockMultipartFile multipartFile = new MockMultipartFile(fileName, fileName + "." + contentType, contentType,
			new FileInputStream(filePath));
		// menuFacade.uploadExcel(multipartFile);
		CustomException exception = assertThrows(CustomException.class, () -> {
			menuFacade.uploadExcel(multipartFile);
		});
		assertEquals("날짜 형식 맞지 않습니다.", exception.getMessage());
	}

	@Test
	public void dataIntoDB_Facade_courseValid() throws Exception {
		String fileName = "april week 1 courseerrormenu";
		String contentType = "xlsx";
		String filePath = "src/test/resources/excel/april week 1 courseerrormenu.xlsx";
		MockMultipartFile multipartFile = new MockMultipartFile(fileName, fileName + "." + contentType, contentType,
			new FileInputStream(filePath));
		menuFacade.uploadExcel(multipartFile);
		Throwable exception = assertThrows(CustomException.class, () -> {
			menuFacade.uploadExcel(multipartFile);
		});
		assertEquals("존재하지 않는 코스 입니다.", exception.getMessage());
	}

	@Test
	public void dataIntoDB_Duplicate_Test() throws Exception {
		String fileName = "april week 1 menu";
		String contentType = "xlsx";
		String filePath = "src/test/resources/excel/april week 1 menu.xlsx";
		MockMultipartFile multipartFile = new MockMultipartFile(fileName, fileName + "." + contentType, contentType,
			new FileInputStream(filePath));
		menuFacade.uploadExcel(multipartFile);
		Throwable exception = assertThrows(CustomException.class, () -> {
			menuFacade.uploadExcel(multipartFile);
		});
		assertEquals("올린 기록이 있는 엑셀 파일입니다.", exception.getMessage());
	}

	@Test
	public void dataIntoDB_Update_Test() throws Exception {
		String fileName = "april week 1 menu";
		String contentType = "xlsx";
		String filePath = "src/test/resources/excel/april week 1 menu.xlsx";
		MockMultipartFile multipartFile = new MockMultipartFile(fileName, fileName + "." + contentType, contentType,
			new FileInputStream(filePath));
		menuFacade.uploadExcel(multipartFile);
		fileName = "april week 1 newmenu";
		contentType = "xlsx";
		filePath = "src/test/resources/excel/april week 1 newmenu.xlsx";
		MockMultipartFile multipartFileUpdate = new MockMultipartFile(fileName, fileName + "." + contentType,
			contentType,
			new FileInputStream(filePath));
		menuFacade.updateAfterDeleteExcel(multipartFileUpdate);
	}

	@Test
	void getTest() {
		dayMenuService.getDayMenu("2024-04-02 22:44:55");
	}
}
