package team.gwon.haveameal.member.controller;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import team.gwon.haveameal.common.component.swagger.SwaggerApiBadRequest;
import team.gwon.haveameal.common.component.swagger.SwaggerApiCreated;
import team.gwon.haveameal.common.component.swagger.SwaggerApiSuccess;
import team.gwon.haveameal.member.domain.EmailCheckDto;
import team.gwon.haveameal.member.domain.LoginRequestDto;
import team.gwon.haveameal.member.domain.MemberFindDto;
import team.gwon.haveameal.member.domain.MemberRegisterDto;
import team.gwon.haveameal.member.domain.MemberResponse;
import team.gwon.haveameal.member.service.MailService;
import team.gwon.haveameal.member.service.MemberService;

@Tag(name = "Member", description = "Member 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

	private final MemberService memberService;
	private final MailService mailService;

	@SwaggerApiCreated(summary = "회원가입", implementation = MemberResponse.class)
	@PostMapping("")
	public ResponseEntity<MemberResponse> insertMember(@RequestBody MemberRegisterDto member) throws IOException {
		memberService.insertMember(member);
		return ResponseEntity.status(HttpStatus.CREATED).body(MemberResponse.CREATE_MEMBER);
	}

	@Deprecated
	@GetMapping("/{memberId}")
	public ResponseEntity<MemberFindDto> getMember(@PathVariable String memberId) {
		MemberFindDto memberFindDto = memberService.getMemberById(memberId);
		if (memberFindDto != null) {
			return ResponseEntity.status(HttpStatus.OK).body(memberFindDto);
		} else {
			return ResponseEntity.badRequest().build();
		}
	}

	@SwaggerApiCreated(summary = "이메일 인증코드 발급", implementation = MemberResponse.class)
	@PostMapping("/email")
	public ResponseEntity<MemberResponse> mailSend(@RequestBody MemberRegisterDto member) {
		mailService.joinEmail(member.getMemberId());
		return ResponseEntity.status(HttpStatus.CREATED).body(MemberResponse.CREATE_AUTH_CODE);
	}

	@SwaggerApiSuccess(summary = "이메일 인증코드 확인", implementation = MemberResponse.class)
	@SwaggerApiBadRequest
	@PostMapping("/emailCheck")
	public ResponseEntity<MemberResponse> authCheck(@RequestBody EmailCheckDto emailCheckDto) {
		boolean checked = mailService.checkAuthNum(emailCheckDto.getEmail(), emailCheckDto.getAuthNum());
		if (checked) {
			return ResponseEntity.status(HttpStatus.CREATED).body(MemberResponse.SUCCESS_AUTH_CODE);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(MemberResponse.FAIL_AUTH_CODE);
		}
	}

	@SwaggerApiSuccess(summary = "로그인", implementation = MemberFindDto.class)
	@SwaggerApiBadRequest
	@PostMapping("/login")
	public ResponseEntity<MemberFindDto> login(@RequestBody LoginRequestDto loginRequestDto) {
		boolean isAuthenticated = memberService.authenticate(loginRequestDto.getMemberId(),
			loginRequestDto.getPassword());
		if (isAuthenticated) {
			MemberFindDto member = memberService.getMemberById(loginRequestDto.getMemberId());
			if (member != null) {
				return ResponseEntity.status(HttpStatus.OK).body(member);
			}
		}
		throw new RuntimeException();
	}
}
