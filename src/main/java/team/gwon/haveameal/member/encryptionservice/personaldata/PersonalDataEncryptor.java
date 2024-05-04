package team.gwon.haveameal.member.encryptionservice.personaldata;

public interface PersonalDataEncryptor {
	String encryptData(String plainData);

	String decryptData(String encryptedData);
}
