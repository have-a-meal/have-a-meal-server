package team.gwon.haveameal.member.domain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum MemberResponse {
	CREATE_MEMBER("회원가입이 정상적으로 처리되었습니다."),
	LOGIN_SUCCESS("로그인이 정상적으로 처리되었습니다."),
	LOGIN_FAIL("로그인에 실패하였습니다."),
	MEMBER_NOT_FOUND("회원을 찾을 수 없습니다."),
	CREATE_AUTH_CODE("인증코드를 발급했습니다."),
	SUCCESS_AUTH_CODE("이메일 인증 완료."),
	FAIL_AUTH_CODE("인증코드가 틀렸습니다.");

	private final String message;

}
