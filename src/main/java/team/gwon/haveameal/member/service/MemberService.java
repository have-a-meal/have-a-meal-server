package team.gwon.haveameal.member.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import team.gwon.haveameal.member.domain.MemberEntity;
import team.gwon.haveameal.member.domain.MemberFindDto;
import team.gwon.haveameal.member.domain.MemberRegisterDto;
import team.gwon.haveameal.member.mapper.MemberMapper;

@Service
@RequiredArgsConstructor
public class MemberService {

	private final MemberMapper memberMapper;

	public void insertMember(MemberRegisterDto memberDto) {
		MemberEntity memberEntity = memberDto.toMemberEntity();
		memberMapper.insertMember(memberEntity);
	}

	public MemberFindDto getMemberById(String memberId) {
		MemberEntity memberEntity = memberMapper.getMemberById(memberId);
		return MemberFindDto.toMemberFindDto(memberEntity);
	}
}
