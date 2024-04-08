package team.gwon.haveameal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import team.gwon.haveameal.domain.Member;
import team.gwon.haveameal.mapper.MemberMapper;

@Service
@Transactional
public class MemberService {
	@Autowired
	private MemberMapper memberMapper;

	public void insertMember(Member member) {
		memberMapper.insertMember(member);
	}
}
