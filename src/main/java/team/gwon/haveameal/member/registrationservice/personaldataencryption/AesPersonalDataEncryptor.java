package team.gwon.haveameal.member.registrationservice.personaldataencryption;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AesPersonalDataEncryptor implements PersonalDataEncryptor {
	public static String alg = "AES/CBC/PKCS5Padding";    // 암호화 시 사용될 알고리즘
	private final SecureRandom secureRandom;
	private final Map<String, String> encryptionMap = new HashMap<>();
	// KMS Server에서 key 관리.
	private final String selectedKey = "0123456789abcdef0123456789abcdef";

	@Override
	public String encryptData(String plainData) {    // 암호화
		try {
			Cipher cipher = Cipher.getInstance(alg);
			SecretKeySpec secretKey = new SecretKeySpec(selectedKey.getBytes(), "AES");
			IvParameterSpec ivParamSpec = generateRandomIv();
			byte[] initializationVector = ivParamSpec.getIV();
			cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParamSpec);
			byte[] encryptedBytes = cipher.doFinal(plainData.getBytes(StandardCharsets.UTF_8));
			encryptionMap.put(selectedKey, Base64.getEncoder().encodeToString(initializationVector));
			return Base64.getEncoder().encodeToString(encryptedBytes);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String decryptData(String encryptedData) {    // 복호화
		try {
			if (encryptedData == null) {
				return null;
			}
			String encodedIv = encryptionMap.get(selectedKey);
			Cipher cipher = Cipher.getInstance(alg);
			SecretKeySpec secretKey = new SecretKeySpec(selectedKey.getBytes(), "AES");
			byte[] initializationVector = Base64.getDecoder().decode(encodedIv);
			IvParameterSpec ivParamSpec = new IvParameterSpec(initializationVector);
			cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParamSpec);
			byte[] decodedBytes = Base64.getDecoder().decode(encryptedData);
			byte[] decryptedText = cipher.doFinal(decodedBytes);
			return new String(decryptedText, StandardCharsets.UTF_8);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private IvParameterSpec generateRandomIv() {
		byte[] iv = new byte[16];
		secureRandom.nextBytes(iv);
		return new IvParameterSpec(iv);
	}
}
