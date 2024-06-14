package team.gwon.haveameal.member.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import team.gwon.haveameal.member.domain.MemberEntity;
import team.gwon.haveameal.member.mapper.MemberMapper;
import team.gwon.haveameal.member.registrationservice.passwordencryption.BCryptPasswordEncryptor;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {
	private final MemberMapper memberMapper;
	private final BCryptPasswordEncryptor passwordEncryptor;

	public boolean authenticate(String id, String password) {
		MemberEntity member;
		if (id.matches("^2\\d{7}")) {    // 학생
			member = memberMapper.getMemberById(id);
			log.info("학생 로그인 시도. id : {}", id);
		} else {    // 외부인
			// 입력받은 전화번호로 kms 서버에서 ID 값을 받아옴.
			member = memberMapper.getMemberById(id);
			log.info("외부인 로그인 시도. id : {}", id);
		}

		if (member == null) {
			log.warn("회원 정보를 찾을 수 없습니다. ID : {}", id);
			return false;
		}

		return passwordEncryptor.matchPassword(password, member.getPassword());
	}
}
