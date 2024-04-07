package team.gwon.haveameal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import team.gwon.haveameal.excelExtract.controller.ExcelExtractController;

import java.io.FileInputStream;

@SpringBootTest
class HaveAMealServerApplicationTests {

    @Autowired
    public ExcelExtractController excelExtractController;

    @Test
    void contextLoads() throws Exception {
        MultipartFile multipartFile = new MockMultipartFile("test.xlsx", new FileInputStream("D:\\extest.xlsx"));
        excelExtractController.ExcelRead(multipartFile);
    }
}
