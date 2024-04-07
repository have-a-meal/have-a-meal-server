package team.gwon.haveameal.excelExtract.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import team.gwon.haveameal.excelExtract.service.ExcelExtractService;

@RestController
@RequiredArgsConstructor
public class ExcelExtractController {
    private final ExcelExtractService excelExtractService;

    @PostMapping("/upload")
    public void ExcelRead(@RequestPart(value = "file", required = false) MultipartFile multipartFile) throws Exception {
        excelExtractService.excelUpload(multipartFile);
    }

}
