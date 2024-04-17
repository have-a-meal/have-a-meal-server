package team.gwon.haveameal.member.memberinfoencryption;

public interface AesMemberInfoEncryptionService {
	String encryptInfo(String plainInfo);

	String decryptInfo(String encryptionData);
}
