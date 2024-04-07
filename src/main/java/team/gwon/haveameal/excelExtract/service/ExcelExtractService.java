package team.gwon.haveameal.excelExtract.service;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Iterator;

@Service
public class ExcelExtractService {

    public static void excelUpload(MultipartFile multipartFile) throws IOException {
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

                }
            }
        }
    }
}
