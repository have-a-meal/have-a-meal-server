package team.gwon.haveameal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import team.gwon.haveameal.domain.Member;
import team.gwon.haveameal.service.MemberService;

@RestController
public class MemberController {
	@Autowired
	private MemberService memberService;

	@PostMapping("/member")
	public void insertMember(@RequestBody Member member) {
		memberService.insertMember(member);
	}

}
