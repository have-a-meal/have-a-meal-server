package team.gwon.haveameal.register.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import team.gwon.haveameal.register.domain.MemberRegister;
import team.gwon.haveameal.register.service.MemberService;

@RestController
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;

	@PostMapping("/insert")
	public void insertMember(@RequestBody MemberRegister member) {
		memberService.insertMember(member);
	}
}
