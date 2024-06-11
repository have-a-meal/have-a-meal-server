package team.gwon.haveameal.member.registrationservice.personaldataencryption;

import team.gwon.haveameal.member.domain.Key;

public interface PersonalDataEncryptor {
	String encryptData(String plainData, Key key);

	String decryptData(String encryptedData, Key key);
}
