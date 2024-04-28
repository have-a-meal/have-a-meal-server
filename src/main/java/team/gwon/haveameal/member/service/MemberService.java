package team.gwon.haveameal.member.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import team.gwon.haveameal.member.domain.MemberRegisterDto;
import team.gwon.haveameal.member.encryptionservice.EncryptionService;
import team.gwon.haveameal.member.mapper.MemberMapper;

@Service
@RequiredArgsConstructor
public class MemberService {

	private final MemberMapper memberMapper;

	private final EncryptionService encryptionService;

	public void insertMember(MemberRegisterDto member) {
		String encryptedPassword = encryptionService.encryptPassword(member.getPassword());
		member.setPassword(encryptedPassword);

		memberMapper.insertMember(member);
	}
}
