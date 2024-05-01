package team.gwon.haveameal.member.encryptionservice.personaldata;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AesPersonalDataEncryptor implements PersonalDataEncryptor, PersonalDataDecryptor {
	public static String alg = "AES/CBC/PKCS5Padding";    // 암호화 시 사용될 알고리즘

	/*
	임의의 키 값. 어떻게 생성하고 어떻게 저장할지 생각할 것.
	 */
	private static final String[] aesKeys = {
		"0123456789abcdef0123456789abcdef",
		"abcdef0123456789abcdef0123456789",
		"0a12sd1f321q321w321q5efa3sd21f35",
		"1f6a54qw3e25f13a5sd41v32c1vqa35r",
		"5jrety4j3kh2gm1ngs3r54g32ad1gna3"
	};

	private final SecureRandom secureRandom;
	private final Map<String, String> encryptionMap = new HashMap<>();

	@Override
	public String encryptData(String plainData) {
		try {
			String selectedKey = aesKeys[secureRandom.nextInt(aesKeys.length)];
			Cipher cipher = Cipher.getInstance(alg);
			SecretKeySpec secretKey = new SecretKeySpec(selectedKey.getBytes(), "AES");
			IvParameterSpec ivParamSpec = generateRandomIv();
			byte[] initializationVector = ivParamSpec.getIV();
			cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParamSpec);

			byte[] encryptedBytes = cipher.doFinal(plainData.getBytes(StandardCharsets.UTF_8));
			String encryptionId = generateEncryptionId();
			String encryptedText = Base64.getEncoder().encodeToString(encryptedBytes);
			encryptionMap.put(encryptionId,
				selectedKey + "," + Base64.getEncoder().encodeToString(initializationVector));
			return encryptionId + ":" + encryptedText;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String decryptData(String encryptedData) {
		try {
			String[] parts = encryptedData.split(":");
			String encryptionId = parts[0];
			String cipherInfo = parts[1];

			String encryptionInfo = encryptionMap.get(encryptionId);
			if (encryptionInfo == null) {
				return null;
			}

			String[] encryptionParts = encryptionInfo.split(",");
			String selectedKey = encryptionParts[0];
			Cipher cipher = Cipher.getInstance(alg);
			SecretKeySpec secretKey = new SecretKeySpec(selectedKey.getBytes(), "AES");
			byte[] initializationVector = Base64.getDecoder().decode(encryptionParts[1]);
			IvParameterSpec ivParamSpec = new IvParameterSpec(initializationVector);
			cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParamSpec);

			byte[] decodedBytes = Base64.getDecoder().decode(cipherInfo);
			byte[] decrypted = cipher.doFinal(decodedBytes);
			return new String(decrypted, StandardCharsets.UTF_8);
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

	private String generateEncryptionId() {
		return UUID.randomUUID().toString();
	}
}
