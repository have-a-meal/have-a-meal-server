package team.gwon.haveameal.excelExtract.component;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Iterator;

@Component
public class ExtractData {

    public static void extract(MultipartFile multipartFile) throws Exception {
        String extension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());

        Workbook workbook = new XSSFWorkbook(multipartFile.getInputStream());
        Iterator<Sheet> sheetIterator = workbook.sheetIterator();

        while (sheetIterator.hasNext()) {
            Sheet sheet = sheetIterator.next();
            Iterator<Row> rowIterator = sheet.rowIterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    System.out.println(cell.getStringCellValue());
                    System.out.println("============");
                }
            }
        }
    }
}
