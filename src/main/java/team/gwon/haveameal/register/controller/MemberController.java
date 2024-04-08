package team.gwon.haveameal.register.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import team.gwon.haveameal.register.domain.MemberRegister;
import team.gwon.haveameal.register.service.MemberService;

@RestController
public class MemberController {
	private MemberService memberService;

	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	@PostMapping("/member")
	public void insertMember(@RequestBody MemberRegister member) {
		memberService.insertMember(member);
	}

}
