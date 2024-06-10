package team.gwon.haveameal.member.registrationservice.passwordencryption;

public interface PasswordEncryptor {
	String encryptPassword(String plainPassword);

	// 비밀번호 대조 기능
	boolean matchPassword(String plainPassword, String encryptedPassword);
}
