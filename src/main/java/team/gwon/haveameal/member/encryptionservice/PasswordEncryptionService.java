package team.gwon.haveameal.member.encryptionservice;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncryptionService implements EncryptionService {
	@Override
	public String encryptPassword(String plainPassword) {
		return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
	}

	@Override
	public String encryptInfo(String plainInfo) {
		return null;    // MemberInfoEncryptionService에서 구현
	}

	@Override
	public String decryptInfo(String encryptionData) {
		return null;    // MemberInfoEncryptionService에서 구현
	}
}
