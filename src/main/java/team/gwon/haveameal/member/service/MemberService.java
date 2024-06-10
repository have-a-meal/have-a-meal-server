package team.gwon.haveameal.member.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import team.gwon.haveameal.member.domain.MemberRegisterDto;
import team.gwon.haveameal.member.mapper.MemberMapper;
import team.gwon.haveameal.member.passwordencryption.PasswordEncryptionService;

@Service
@RequiredArgsConstructor
public class MemberService {

	private final MemberMapper memberMapper;

	private final PasswordEncryptionService passwordEncryptionService;

	public void insertMember(MemberRegisterDto member) {
		String encryptedPassword = passwordEncryptionService.encryptPassword(member.getPassword());
		member.setPassword(encryptedPassword);

		memberMapper.insertMember(member);
	}
}
