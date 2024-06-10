package team.gwon.haveameal.member.registrationservice.personaldataencryption;

public interface PersonalDataEncryptor {
	String encryptData(String plainData);

	String decryptData(String encryptedData);
}
