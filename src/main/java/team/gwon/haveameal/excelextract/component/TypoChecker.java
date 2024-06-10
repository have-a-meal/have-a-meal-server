package team.gwon.haveameal.excelextract.component;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import team.gwon.haveameal.excelextract.dto.ValidFood;
import team.gwon.haveameal.excelextract.util.ApiRequestUtil;

@Component
@Slf4j
public class TypoChecker {

	@Value("${NAVER-CLIENT}")
	private String clientId;

	@Value("${NAVER-KEY}")
	private String clientSecret;

	@Value("${NAVER-URL}")
	private String url;

	public String check(String word) throws JsonProcessingException {
		log.info("Checking word: " + word);
		String replaceWord = word;
		String text = null;
		try {
			text = URLEncoder.encode(word, "UTF-8");

		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("검색어 인코딩 실패", e);
		}

		String apiUrl = url + text;

		Map<String, String> requestHeaders = new HashMap<>();
		requestHeaders.put("X-Naver-Client-Id", clientId);
		requestHeaders.put("X-Naver-Client-Secret", clientSecret);
		String responseBody = ApiRequestUtil.get(apiUrl, requestHeaders);
		log.info("Response body: " + responseBody);
		// return word;
		ObjectMapper objectMapper = new ObjectMapper();
		ValidFood validFood = objectMapper.readValue(responseBody, ValidFood.class);
		// String checkedFood = objectMapper.readValue(responseBody, String.class);
		// if (checkedFood.isEmpty()) {
		// 	return word;
		// }
		// return checkedFood;
		if (validFood.getErrata().isEmpty()) {
			return word;
		}
		return validFood.getErrata();
		// check(validFood.getErrata());
		// // return word;
		//
		// return word;
		// if (validFood.getErrata() != null) {
		// 	replaceWord = check(validFood.getErrata());
		// }
		// return replaceWord;
		// if (validFood.getErrata() == null) {
		// 	log.info("no changing");
		// 	return word;
		// } else {
		// 	log.info("changing");
		// 	return validFood.getErrata();
		// }
	}
}
