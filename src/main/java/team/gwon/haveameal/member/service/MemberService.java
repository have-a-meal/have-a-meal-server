package team.gwon.haveameal.member.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import team.gwon.haveameal.member.domain.MemberRegister;
import team.gwon.haveameal.member.mapper.MemberMapper;

@Service
@RequiredArgsConstructor
public class MemberService {

	private final MemberMapper memberMapper;

	public void insertMember(MemberRegister member) {
		memberMapper.insertMember(member);
	}
}
