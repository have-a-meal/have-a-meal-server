package team.gwon.haveameal.excelExtract.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import team.gwon.haveameal.excelExtract.component.ExtractData;

@Service
public class ExcelExtractService {

    public static void excelUpload(MultipartFile multipartFile) throws Exception {
        ExtractData.extract(multipartFile);
    }
}
