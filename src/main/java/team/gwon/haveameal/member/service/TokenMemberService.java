package team.gwon.haveameal.member.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import team.gwon.haveameal.member.converter.ToFindDtoConverter;
import team.gwon.haveameal.member.domain.MemberEntity;
import team.gwon.haveameal.member.mapper.MemberMapper;

@Service
@RequiredArgsConstructor
public class TokenMemberService {

	private final MemberMapper memberMapper;
	private final ToFindDtoConverter toFindDtoConverter;

	public MemberEntity getUsedMemberId(String memberId) {
		return memberMapper.getMemberById(memberId);
	}
}
