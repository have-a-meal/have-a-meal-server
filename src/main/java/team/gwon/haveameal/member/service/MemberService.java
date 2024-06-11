package team.gwon.haveameal.member.service;

import java.io.IOException;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import team.gwon.haveameal.member.converter.ToEntityConverter;
import team.gwon.haveameal.member.converter.ToFindDtoConverter;
import team.gwon.haveameal.member.domain.MemberEntity;
import team.gwon.haveameal.member.domain.MemberFindDto;
import team.gwon.haveameal.member.domain.MemberRegisterDto;
import team.gwon.haveameal.member.mapper.MemberMapper;
import team.gwon.haveameal.member.registrationservice.passwordencryption.BCryptPasswordEncryptor;

@Service
@RequiredArgsConstructor
public class MemberService {

	private final MemberMapper memberMapper;
	private final ToEntityConverter toEntityConverter;
	private final ToFindDtoConverter toFindDtoConverter;
	private final BCryptPasswordEncryptor passwordEncryptor;

	public void insertMember(MemberRegisterDto memberDto) throws IOException {
		MemberEntity memberEntity = toEntityConverter.toMemberEntity(memberDto);
		memberMapper.insertMember(memberEntity);
	}

	public MemberFindDto getMemberById(String memberId) {
		MemberEntity memberEntity = memberMapper.getMemberById(memberId);
		return toFindDtoConverter.toMemberFindDto(memberEntity);
	}

	public boolean authenticate(String memberId, String password) {
		MemberEntity member = memberMapper.getMemberById(memberId);
		if (member != null) {
			return passwordEncryptor.matchPassword(password, member.getPassword());
		}
		return false;
	}
}
