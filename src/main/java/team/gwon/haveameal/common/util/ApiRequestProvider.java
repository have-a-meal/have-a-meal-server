package team.gwon.haveameal.common.util;

import java.io.IOException;
import java.util.Map;

import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class ApiRequestProvider {

	private final ObjectMapper objectMapper;

	public <T> String post(String apiUrl, T requestBody) throws IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		try {
			HttpPost httpPost = new HttpPost(apiUrl);
			httpPost.setHeader("Content-Type", ContentType.APPLICATION_JSON.toString());
			StringEntity entity = new StringEntity(objectMapper.writeValueAsString(requestBody));
			httpPost.setEntity(entity);
			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			return httpClient.execute(httpPost, responseHandler);
		} catch (IOException e) {
			throw new IOException(e.getMessage());
		} finally {
			httpClient.close();
		}
	}

	public String get(String apiUrl, Map<String, String> requestHeaders) throws IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		try {
			HttpGet httpGet = new HttpGet(apiUrl);
			for (Map.Entry<String, String> header : requestHeaders.entrySet()) {
				httpGet.setHeader(header.getKey(), header.getValue());
			}
			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			return httpClient.execute(httpGet, responseHandler);
		} catch (IOException e) {
			throw new IOException(e.getMessage());
		} finally {
			httpClient.close();
		}
	}
}
