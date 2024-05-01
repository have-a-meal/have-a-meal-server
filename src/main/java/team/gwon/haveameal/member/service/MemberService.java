package team.gwon.haveameal.member.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import team.gwon.haveameal.member.domain.MemberRegisterDto;
import team.gwon.haveameal.member.encryptionservice.password.PasswordEncryptor;
import team.gwon.haveameal.member.encryptionservice.personaldata.PersonalDataEncryptor;
import team.gwon.haveameal.member.mapper.MemberMapper;

@Service
@RequiredArgsConstructor
public class MemberService {

	private final MemberMapper memberMapper;

	private final PasswordEncryptor passwordEncryptor;
	private final PersonalDataEncryptor personalDataEncryptor;

	public void insertMember(MemberRegisterDto member) {
		String encryptedPassword = passwordEncryptor.encryptPassword(member.getPassword());
		member.setPassword(encryptedPassword);
		String encryptedId = personalDataEncryptor.encryptData(member.getMemberId());
		member.setMemberId(encryptedId);
		String encryptedName = personalDataEncryptor.encryptData(member.getName());
		member.setName(encryptedName);
		String encryptedPhone = personalDataEncryptor.encryptData(member.getPhone());
		member.setPhone(encryptedPhone);

		memberMapper.insertMember(member);
	}
}
