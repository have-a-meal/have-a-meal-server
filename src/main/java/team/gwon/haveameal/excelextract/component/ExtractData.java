package team.gwon.haveameal.excelextract.component;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ExtractData {

	private final DataRegex dataRegex;

	public List<Map<String, Object>> extract(MultipartFile multipartFile) throws Exception {
		String extension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
		Workbook workbook;
		if (extension.equals("xlsx")) {
			workbook = new XSSFWorkbook(multipartFile.getInputStream()); // Excel 2007
		} else if (extension.equals("xls")) {
			workbook = new HSSFWorkbook(multipartFile.getInputStream()); // Excel 2003
		} else {
			throw new IOException("엑셀 파일이 아닙니다.");
		}
		if (workbook.getNumberOfSheets() == 0 || workbook.getSheetAt(0).getPhysicalNumberOfRows() == 0) {
			throw new IllegalStateException("엑셀 파일에 시트가 존재하지 않습니다.");
		}
		List<Map<String, Object>> mergedList = new ArrayList<>();
		for (int i = 0; i < 3; i++) { // workbook.getNumberOfSheets() == 3
			Sheet sheet = workbook.getSheetAt(i);
			List<Map<String, Object>> excelData = new ArrayList<>();
			Map<String, Object> cellData;
			for (int j = 0; j < 4; j++) { // sheet.getPhysicalNumberOfRows() == 4
				Row row = sheet.getRow(j);
				for (int k = 0; k < 8; k++) { // row.getPhysicalNumberOfCells() == 6
					Cell cell = row.getCell(k);
					Object data = dataRegex.excelToMap(cell); //리턴 타입 ; list, date, bool, string, integer
					if (data instanceof Boolean) {
						continue;
					}
					if (data instanceof Date) {
						cellData = new HashMap<>();
						cellData.put("timing", sheet.getSheetName());
						cellData.put("date", data);
						excelData.add(cellData);
					}
					if (data instanceof Integer) {
						excelData.get(k - 1).put("meal", "");
					}
					if (data instanceof String) {
						for (Map<String, Object> map : excelData) {
							map.put("course", data);
						}
					}
					if (data instanceof List<?>) {
						excelData.get(k - 1).put("meal", data);
					}
				}
				if (excelData.get(0).containsKey("course") && excelData.get(0).containsKey("meal")) {
					for (Map<String, Object> map : excelData) {
						Map<String, Object> mergedMap = new HashMap<>(map);
						mergedList.add(mergedMap);
					}
				}

			}
		}
		return mergedList;

	}
}
