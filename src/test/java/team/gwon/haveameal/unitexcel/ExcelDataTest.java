package team.gwon.haveameal.unitexcel;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;
import team.gwon.haveameal.excelextract.component.DataRegex;

@Slf4j
public class ExcelDataTest {

	Workbook workbook = new XSSFWorkbook();
	Sheet sheet = workbook.createSheet("중식");
	Row row = sheet.createRow(0);
	Cell cell = row.createCell(0);

	@Test
	void courceDateDataTest() {
		cell.setCellValue("코스/일자");
		Object data = DataRegex.excelToMap(cell);
		if (data instanceof Boolean) {
			log.info("success");
		} else {
			log.info("fail");
		}
	}

	@Test
	void dateDataTest() {
		cell.setCellValue("04월09일 화");
		Object data = DataRegex.excelToMap(cell);
		if (data instanceof Date) {
			log.info(data.toString());
		}
	}

	@Test
	void courceDataTest() {
		cell.setCellValue("A");
		Object data = DataRegex.excelToMap(cell);
		if (data instanceof String) {
			log.info(data.toString());
		}
	}

	@Test
	void mealDataTest() {
		cell.setCellValue("김치찌개\n쌀밥\n분홍 소시지\n어묵볶음");
		Object data = DataRegex.excelToMap(cell);
		if (data instanceof List<?>) {
			log.info(data.toString());
		}
	}

	@Test
	void timingDataTest() {
		log.info(sheet.getSheetName());
	}

	@Test
	void blankDataTest() {
		cell.setBlank();
		Object data = DataRegex.excelToMap(cell);
		if (data instanceof Integer) {
			log.info("blank data");
		} else {
			log.info("Not blank data");
		}
	}

	@Test
	void stringConvertdate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.set(calendar.get(Calendar.YEAR), 4, 6);
		//month는 0 == January로 시작
		log.info(sdf.format(calendar.getTime()));
		log.info(sdf.getClass().getName());
		String formatDate = sdf.format(calendar.getTime());
		Date sqlDate = Date.valueOf(formatDate);
		log.info(String.valueOf(sqlDate));
		log.info(sqlDate.getClass().getName());
	}
}
