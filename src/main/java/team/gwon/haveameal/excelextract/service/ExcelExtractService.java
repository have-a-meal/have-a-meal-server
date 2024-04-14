package team.gwon.haveameal.excelextract.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import team.gwon.haveameal.excelextract.component.ExtractData;

@Service
@RequiredArgsConstructor
public class ExcelExtractService {

	private final ExtractData extractData;

	public List<Map<String, Object>> excelUpload(MultipartFile multipartFile) throws Exception {
		List<Map<String, Object>> data = extractData.extract(multipartFile);
		return data;
	}
}
