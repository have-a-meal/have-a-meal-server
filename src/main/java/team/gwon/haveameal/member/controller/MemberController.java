package team.gwon.haveameal.member.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import team.gwon.haveameal.member.domain.MemberRegister;
import team.gwon.haveameal.member.service.MemberService;

@RestController
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;

	@PostMapping("/insert")
	public void insertMember(@RequestBody MemberRegister member) {
		memberService.insertMember(member);
	}
}
