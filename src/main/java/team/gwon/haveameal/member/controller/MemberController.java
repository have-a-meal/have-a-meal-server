package team.gwon.haveameal.member.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import team.gwon.haveameal.member.domain.MemberRegisterDto;
import team.gwon.haveameal.member.service.MemberService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

	private final MemberService memberService;

	@PostMapping("/insert")
	public void insertMember(@RequestBody MemberRegisterDto member) {
		memberService.insertMember(member);
	}

	@GetMapping("/{memberId}")
	public MemberRegisterDto getMember(@PathVariable String memberId) {
		return memberService.getMemberById(memberId);
	}
}
