package team.gwon.haveameal.common.logging;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import org.springframework.util.StreamUtils;

import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

public class CachedHttpServletRequest extends HttpServletRequestWrapper {

	private String cachedRequestData;

	/**
	 * Constructs a request object wrapping the given request.
	 *
	 * @param request The request to wrap
	 * @throws IllegalArgumentException if the request is null
	 */

	public CachedHttpServletRequest(HttpServletRequest request) throws IOException {
		super(request);
		this.cachedRequestData = requestDataToByte(request);
	}

	private String requestDataToByte(HttpServletRequest request) throws IOException {
		ServletInputStream inputStream = request.getInputStream();
		byte[] rawData = StreamUtils.copyToByteArray(inputStream);
		return new String(rawData);
	}

	@Override
	public ServletInputStream getInputStream() {
		ByteArrayInputStream inputStream = new ByteArrayInputStream(
			this.cachedRequestData.getBytes(StandardCharsets.UTF_8));
		return new CachedServletInputStream(inputStream);
	}

	@Override
	public BufferedReader getReader() {
		return new BufferedReader(new InputStreamReader(this.getInputStream()));
	}

	public String getCachedRequestData() {
		return this.cachedRequestData;
	}

}
