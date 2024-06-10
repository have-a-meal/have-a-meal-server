package team.gwon.haveameal.common.logging;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletInputStream;

public class CachedServletInputStream extends ServletInputStream {
	private final ByteArrayInputStream inputStream;

	// inputStream를 모두 읽은 경우 true 리턴합니다.
	@Override
	public boolean isFinished() {
		return inputStream.available() == 0;
	}

	// 스트림을 읽을 수 있습니다.
	@Override
	public boolean isReady() {
		return inputStream.available() != 0;
	}

	// 서블릿 3.1부터 비동기 읽기 작업을 위해 도입되었습니다.
	// 비동기 읽기 작업을 지원하지 않는다는 것을 의미합니다.
	@Override
	public void setReadListener(ReadListener listener) {
		throw new UnsupportedOperationException("비동기 읽기는 이 클래스에서 지원되지 않습니다.");
	}

	// 전달받은 inputStream을 읽기 위한 설정입니다.
	@Override
	public int read() throws IOException {
		return inputStream.read();
	}

	public CachedServletInputStream(ByteArrayInputStream inputStream) {
		this.inputStream = inputStream;
	}

}
