package team.gwon.haveameal.member.util;

import java.util.Random;

public class IdGenerator {
	private static final Random random = new Random();

	/*
	DB에 저장된 값과 비교 필요.
	*/
	public static String generateId() {
		int id = random.nextInt(9000000) + 1000000;
		return String.valueOf(id);
	}
}
