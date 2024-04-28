package team.gwon.haveameal.member.encryptionservice;

public interface EncryptionService {
	String encryptPassword(String plainPassword);

	String encryptInfo(String plainInfo);

	String decryptInfo(String encryptionData);
}
