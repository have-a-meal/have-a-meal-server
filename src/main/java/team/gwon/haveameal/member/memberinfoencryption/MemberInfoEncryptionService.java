package team.gwon.haveameal.member.memberinfoencryption;

import org.springframework.stereotype.Component;

@Component
public class MemberInfoEncryptionService implements AesMemberInfoEncryptionService {

	@Override
	public String encryptInfo(String plainInfo) {
		return null;
	}

	@Override
	public String decryptInfo(String encryptionData) {
		return null;
	}
}
