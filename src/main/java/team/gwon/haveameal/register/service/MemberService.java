package team.gwon.haveameal.register.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import team.gwon.haveameal.register.domain.MemberRegister;
import team.gwon.haveameal.register.mapper.MemberMapper;

@Service
@RequiredArgsConstructor
public class MemberService {

	private final MemberMapper memberMapper;

	public void insertMember(MemberRegister member) {
		memberMapper.insertMember(member);
	}
}
