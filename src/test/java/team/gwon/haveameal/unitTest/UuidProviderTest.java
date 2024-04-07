package team.gwon.haveameal.unitTest;


import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;
import team.gwon.haveameal.common.util.UuidProvider;

@Slf4j
class UuidProviderTest {
	@DisplayName("UUID 생성 및 변환 테스트")
	@Test
	void generateSequentialUuid() {

		String[] uuidArr = new String[10];
		List<byte[]> list = new ArrayList<>();
		for (int i = 0; i < uuidArr.length; i++) {
			uuidArr[i] = UuidProvider.generateSequentialUuid();
			list.add(UuidProvider.stringToByte(uuidArr[i]));
		}

		for(int i = 0; i < uuidArr.length; i++){
			log.info("origin String : {} \t byte[] : {} \t revert String : {} ",uuidArr[i],list.get(i),UuidProvider.byteToString(list.get(i)));
			assertEquals(uuidArr[i], UuidProvider.byteToString(list.get(i)));
		}
	}
}