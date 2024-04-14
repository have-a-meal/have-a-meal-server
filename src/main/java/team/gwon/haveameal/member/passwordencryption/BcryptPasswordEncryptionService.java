package team.gwon.haveameal.member.passwordencryption;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class BcryptPasswordEncryptionService implements PasswordEncryptionService {
	@Override
	public String encryptPassword(String plainPassword) {
		return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
	}
}
