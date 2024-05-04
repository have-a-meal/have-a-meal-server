package team.gwon.haveameal.member.encryptionservice.password;

public interface PasswordEncryptor {
	String encryptPassword(String plainPassword);

	// 비밀번호 대조 기능 필요
}
