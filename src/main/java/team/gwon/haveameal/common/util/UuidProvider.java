package team.gwon.haveameal.common.util;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.fasterxml.uuid.Generators;

@Component
public class UuidProvider {
	private UuidProvider() {
	}
	public static String generateSequentialUuid() {
		UUID uuid = Generators.timeBasedGenerator().generate(); // UUID V1 생성
		String[] uuidArr = uuid.toString().split("-"); // 각 필드를 구분 하기 위한 '-' 제거
		// 3번째 일부 값 까지 순차 적인 값을 갖게 할 수 있음
		return uuidArr[2] + uuidArr[1] + uuidArr[0] + uuidArr[3] + uuidArr[4];
	}

	public static byte[] stringToByte(String uuid) {
		byte[] uuidArr = new byte[16];
		for (int i = 0; i < uuid.length(); i += 2) {
			uuidArr[i / 2] = (byte) ((Character.digit(uuid.charAt(i), 16) << 4)
				+ Character.digit(uuid.charAt(i + 1), 16));
		}
		return uuidArr;
	}

	public static String byteToString(byte[] uuidArr) {
		StringBuilder stringBuilder = new StringBuilder();
		for (byte b : uuidArr) {
			String hex = String.format("%02x", b);
			stringBuilder.append(hex);
		}
		return stringBuilder.toString();
	}
}

