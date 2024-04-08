package team.gwon.haveameal.excelextract.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import team.gwon.haveameal.excelextract.component.ExtractData;

@Service
public class ExcelExtractService {

	public List<Map<String, Object>> excelUpload(MultipartFile multipartFile) throws Exception {
		List<Map<String, Object>> data = ExtractData.extract(multipartFile);
		return data;
	}
}
