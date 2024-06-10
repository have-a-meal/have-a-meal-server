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
import team.gwon.haveameal.member.domain.EmailCheckDto;
import team.gwon.haveameal.member.domain.LoginRequestDto;
import team.gwon.haveameal.member.domain.MemberFindDto;
import team.gwon.haveameal.member.domain.MemberRegisterDto;
import team.gwon.haveameal.member.service.MailService;
import team.gwon.haveameal.member.service.MemberService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

	private final MemberService memberService;
	private final MailService mailService;

	@PostMapping("/")
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

	@PostMapping("/email")
	public ResponseEntity<Void> mailSend(@RequestBody MemberRegisterDto member) {
		mailService.joinEmail(member.getMemberId());
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@GetMapping("/emailCheck")
	public String authCheck(@RequestBody EmailCheckDto emailCheckDto) {
		boolean checked = mailService.checkAuthNum(emailCheckDto.getEmail(), emailCheckDto.getAuthNum());
		if (checked) {
			return "ok";
		} else {
			throw new NullPointerException("잘못 입력");
		}
	}

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody LoginRequestDto loginRequestDto) {
		boolean isAuthenticated = memberService.authenticate(loginRequestDto.getMemberId(),
			loginRequestDto.getPassword());
		if (isAuthenticated) {
			return ResponseEntity.ok("Login Successful");
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credentials");
		}
	}
}
