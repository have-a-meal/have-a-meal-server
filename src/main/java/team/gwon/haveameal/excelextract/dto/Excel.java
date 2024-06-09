package team.gwon.haveameal.excelextract.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// @ExcelCheck

public class Excel {

	private MultipartFile multipartFile;

	public Excel() {
	}

	public Excel(MultipartFile multipartFile) {
		this.multipartFile = multipartFile;
	}
}
