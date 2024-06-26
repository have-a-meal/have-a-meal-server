package team.gwon.haveameal.member.registrationservice.passwordencryption;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class BCryptPasswordEncryptor implements PasswordEncryptor {
	@Override
	public String encryptPassword(String plainPassword) {
		return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
	}

	@Override
	public boolean matchPassword(String plainPassword, String encryptedPassword) {
		return BCrypt.checkpw(plainPassword, encryptedPassword);
	}
}
