package team.gwon.haveameal.member.registrationservice.personaldataencryption;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import team.gwon.haveameal.member.domain.Key;

@Component
@RequiredArgsConstructor
public class AesPersonalDataEncryptor implements PersonalDataEncryptor {
	public static final String ALGORITHM = "AES/CBC/PKCS5Padding";    // 암호화 시 사용될 알고리즘

	@Override
	public String encryptData(String plainData, Key key) {    // 암호화
		try {
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			SecretKeySpec secretKey = new SecretKeySpec(key.getEncryptionKey(), "AES");
			IvParameterSpec ivParamSpec = new IvParameterSpec(key.getIv());
			cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParamSpec);
			byte[] encryptedBytes = cipher.doFinal(plainData.getBytes(StandardCharsets.UTF_8));
			return Base64.getEncoder().encodeToString(encryptedBytes);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public String decryptData(String encryptedData, Key key) {    // 복호화
		try {
			if (encryptedData == null) {
				return null;
			}
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			SecretKeySpec secretKey = new SecretKeySpec(key.getEncryptionKey(), "AES");
			IvParameterSpec ivParamSpec = new IvParameterSpec(key.getIv());
			cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParamSpec);
			byte[] decodedBytes = Base64.getDecoder().decode(encryptedData);
			byte[] decryptedText = cipher.doFinal(decodedBytes);
			return new String(decryptedText, StandardCharsets.UTF_8);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
