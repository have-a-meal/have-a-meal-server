package team.gwon.haveameal.member.registrationservice;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import team.gwon.haveameal.member.domain.MemberEntity;
import team.gwon.haveameal.member.domain.MemberRegisterDto;
import team.gwon.haveameal.member.registrationservice.passwordencryption.PasswordEncryptor;
import team.gwon.haveameal.member.registrationservice.personaldataencryption.PersonalDataEncryptor;

@Component
@RequiredArgsConstructor
public class MemberEncryptor {

	private final PasswordEncryptor passwordEncryptor;
	private final PersonalDataEncryptor personalDataEncryptor;

	public MemberRegisterDto encryptMemberData(MemberRegisterDto memberRegisterDto) {
		String encryptedPassword = passwordEncryptor.encryptPassword(memberRegisterDto.getPassword());
		String encryptedName = personalDataEncryptor.encryptData(memberRegisterDto.getName());
		String encryptedPhone = personalDataEncryptor.encryptData(memberRegisterDto.getPhone());
		return new MemberRegisterDto(encryptedPassword, encryptedName, encryptedPhone);
	}

	public MemberEntity decryptMemberData(MemberEntity memberEntity) {
		String decodedName = personalDataEncryptor.decryptData(memberEntity.getName());
		String decodedPhone = personalDataEncryptor.decryptData(memberEntity.getPhone());
		return new MemberEntity(decodedName, decodedPhone);
	}
}
