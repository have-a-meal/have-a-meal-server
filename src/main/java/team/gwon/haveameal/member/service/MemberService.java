package team.gwon.haveameal.member.service;

import java.io.IOException;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import team.gwon.haveameal.common.domain.TokenDto;
import team.gwon.haveameal.common.util.TokenProvider;
import team.gwon.haveameal.member.converter.ToEntityConverter;
import team.gwon.haveameal.member.converter.ToFindDtoConverter;
import team.gwon.haveameal.member.domain.MemberEntity;
import team.gwon.haveameal.member.domain.MemberFindDto;
import team.gwon.haveameal.member.domain.MemberRegisterDto;
import team.gwon.haveameal.member.mapper.MemberMapper;
import team.gwon.haveameal.member.registrationservice.passwordencryption.BCryptPasswordEncryptor;

@Service
@RequiredArgsConstructor
public class MemberService {

	private final MemberMapper memberMapper;
	private final ToEntityConverter toEntityConverter;
	private final ToFindDtoConverter toFindDtoConverter;
	private final BCryptPasswordEncryptor passwordEncryptor;
	private final TokenProvider tokenProvider;

	public void insertMember(MemberRegisterDto memberDto) throws IOException {
		MemberEntity memberEntity = toEntityConverter.toMemberEntity(memberDto);
		memberMapper.insertMember(memberEntity);
	}

	public MemberFindDto getMemberById(String memberId) {
		MemberEntity memberEntity = memberMapper.getMemberById(memberId);
		return toFindDtoConverter.toMemberFindDto(memberEntity);
	}

	public TokenDto authenticate(String memberId, String password) {
		MemberEntity member = memberMapper.getMemberById(memberId);
		if (member != null && passwordEncryptor.matchPassword(password, member.getPassword())) {
			TokenDto tokenDto = tokenProvider.createAllToken(memberId);
			//밑에 과정에서 NPE이나 exp 관련 의심해봐야함.
			tokenProvider.insertRefreshToken(tokenDto.getRefreshToken());
			return tokenDto;
			//검증로직 탈 필요없이 refresh토큰을 redis에 저장시킨 후 반환.
		}
		return null;
	}
}
