package team.gwon.haveameal.excelextract.component;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.springframework.stereotype.Component;

@Component
public class DataRegex {
	private static final String dataRegex = "^[0-9][0-9]월[0-9][0-9]일 [월화수목금]$";
	private static final String courceRegex = "^[a-zA-Z]*$";
	private static final String courceDateRegex = "코스/일자";

	public static Object excelToMap(Cell cellData) {
		if (cellData.getCellType() == CellType.BLANK) {
			return 0;
		}
		switch (match(cellData.getStringCellValue())) {
			case 0:
				String[] foodArr = cellData.getStringCellValue().split("\\n");
				return Arrays.asList(foodArr);
			case 1:
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Calendar calendar = Calendar.getInstance();
				int month = Integer.parseInt(cellData.getStringCellValue().split("")[1]);
				int day = Integer.parseInt(cellData.getStringCellValue().split("")[4]);
				calendar.set(calendar.get(Calendar.YEAR), month - 1, day);
				String formatDate = sdf.format(calendar.getTime());
				return Date.valueOf(formatDate);
			case 2:
				return cellData.getStringCellValue();
			case 3:
				return true;
			default:
				throw new IllegalStateException("형식에 맞지 않은 데이터 : " + cellData.getStringCellValue());
		}
	}

	public static int match(String data) {

		if (data.matches(dataRegex)) {
			return 1;
		}
		if (data.matches(courceRegex)) {
			return 2;
		}
		if (data.matches(courceDateRegex)) {
			return 3;
		}
		return 0;
	}
}
