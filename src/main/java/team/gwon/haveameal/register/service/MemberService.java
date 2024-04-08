package team.gwon.haveameal.register.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import team.gwon.haveameal.register.domain.MemberRegister;
import team.gwon.haveameal.register.mapper.MemberMapper;

@Service
public class MemberService {
	private MemberMapper memberMapper;

	public MemberService(MemberMapper memberMapper) {
		this.memberMapper = memberMapper;
	}

	@Transactional
	public void insertMember(MemberRegister member) {
		memberMapper.insertMember(member);
	}
}
