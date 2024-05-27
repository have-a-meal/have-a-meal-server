package team.gwon.haveameal.member.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import team.gwon.haveameal.member.domain.MemberFindDto;
import team.gwon.haveameal.member.domain.MemberRegisterDto;
import team.gwon.haveameal.member.service.MemberService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

	private final MemberService memberService;

	@PostMapping("")
	public ResponseEntity<Void> insertMember(@RequestBody MemberRegisterDto member) {
		memberService.insertMember(member);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@GetMapping("/{memberId}")
	public ResponseEntity<MemberFindDto> getMember(@PathVariable String memberId) {
		MemberFindDto memberFindDto = memberService.getMemberById(memberId);
		if (memberFindDto != null) {
			return ResponseEntity.ok(memberFindDto);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
