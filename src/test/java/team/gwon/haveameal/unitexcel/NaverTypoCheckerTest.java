package team.gwon.haveameal.unitexcel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NaverTypoCheckerTest {

	@Test
	public void test() {
		String beforeWord = "마라상거";
		log.info("success {} to {}", beforeWord, typoChecker(beforeWord));
	}

	private static String typoChecker(String word) {
		String clientId = "YOUR_CLIENT_ID"; //애플리케이션 클라이언트 아이디
		String clientSecret = "YOUR_CLIENT_SECRET"; //애플리케이션 클라이언트 시크릿

		String text = null;
		try {
			text = URLEncoder.encode(word, "UTF-8");
			//닥거기장조림을 예로 들어봤는데
			//1차적으로 달고기장조림이 나옴 그 후 달고기장조림으로 돌리니까 닭고기 장조림이 나옴.
			//검증로직을 반복함으로써 정확성을 높이는 방향도 괜찮다고 생각됨.
			//옳은 결과일때는 공란을 반환하니까 공란을 반환할때까지 한다는게 좋을듯
			//아예 이상한 말이 나오면 공란을 반환해서 이 부분은 한계라고 생각이 듬.ex)기무치=>공란 나옴.
			//재귀를 사용해서 errata값이 공란일때 해당 text return 해주고 아니면 계속 반복.
			//ex)마라상거->마라샹거->마라샹궈
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("검색어 인코딩 실패", e);
		}

		String apiUrl = "https://openapi.naver.com/v1/search/errata.json?query=" + text;

		Map<String, String> requestHeaders = new HashMap<>();
		requestHeaders.put("X-Naver-Client-Id", clientId);
		requestHeaders.put("X-Naver-Client-Secret", clientSecret);
		String responseBody = get(apiUrl, requestHeaders);
		if (!responseBody.split(":")[1].replace("}", "").equals("\"\"")) {
			word = typoChecker(responseBody.split(":")[1].replaceAll("[\"}]", ""));
		}
		return word;
	}

	private static String get(String apiUrl, Map<String, String> requestHeaders) {
		HttpURLConnection con = connect(apiUrl);
		try {
			con.setRequestMethod("GET");
			for (Map.Entry<String, String> header : requestHeaders.entrySet()) {
				con.setRequestProperty(header.getKey(), header.getValue());
			}

			int responseCode = con.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
				return readBody(con.getInputStream());
			} else { // 오류 발생
				return readBody(con.getErrorStream());
			}
		} catch (IOException e) {
			throw new RuntimeException("API 요청과 응답 실패", e);
		} finally {
			con.disconnect();
		}
	}

	private static HttpURLConnection connect(String apiUrl) {
		try {
			URL url = new URL(apiUrl);
			return (HttpURLConnection)url.openConnection();
		} catch (MalformedURLException e) {
			throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
		} catch (IOException e) {
			throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
		}
	}

	private static String readBody(InputStream body) throws UnsupportedEncodingException {
		InputStreamReader streamReader = new InputStreamReader(body, "utf-8");

		try (BufferedReader lineReader = new BufferedReader(streamReader)) {
			StringBuilder responseBody = new StringBuilder();

			String line;
			while ((line = lineReader.readLine()) != null) {
				responseBody.append(line);
			}

			return responseBody.toString();
		} catch (IOException e) {
			throw new RuntimeException("API 응답을 읽는 데 실패했습니다.", e);
		}
	}
}
