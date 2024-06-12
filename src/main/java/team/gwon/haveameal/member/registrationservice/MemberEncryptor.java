package team.gwon.haveameal.member.registrationservice;

import java.io.IOException;
import java.util.HashMap;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import team.gwon.haveameal.common.component.ApiRequestProvider;
import team.gwon.haveameal.member.domain.Key;
import team.gwon.haveameal.member.domain.KeyGenerateRequestDto;
import team.gwon.haveameal.member.domain.MemberEntity;
import team.gwon.haveameal.member.domain.MemberRegisterDto;
import team.gwon.haveameal.member.registrationservice.passwordencryption.PasswordEncryptor;
import team.gwon.haveameal.member.registrationservice.personaldataencryption.PersonalDataEncryptor;

@Slf4j
@Component
@RequiredArgsConstructor
public class MemberEncryptor {

	private final PasswordEncryptor passwordEncryptor;
	private final PersonalDataEncryptor personalDataEncryptor;
	private final ObjectMapper objectMapper;
	private final ApiRequestProvider apiRequestProvider;

	private static final String KMS_URL = "http://localhost:88/key";
	private static final int BLOCK_LENGTH = 32;

	public MemberRegisterDto encryptMemberData(MemberRegisterDto memberRegisterDto) {
		try {
			String response = apiRequestProvider.post(KMS_URL,
				KeyGenerateRequestDto.of(memberRegisterDto.getMemberId(), BLOCK_LENGTH));

			log.info("response: {}", response);
			Key key = objectMapper.readValue(response, Key.class);

			String encryptedPassword = passwordEncryptor.encryptPassword(memberRegisterDto.getPassword());
			String encryptedName = personalDataEncryptor.encryptData(memberRegisterDto.getName(),
				key);
			String encryptedPhone = personalDataEncryptor.encryptData(memberRegisterDto.getPhone(),
				key);
			return new MemberRegisterDto(encryptedPassword, encryptedName, encryptedPhone);

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public MemberEntity decryptMemberData(MemberEntity memberEntity) {
		try {
			String response = apiRequestProvider.get(KMS_URL + "/" + memberEntity.getMemberId(), new HashMap<>());

			Key key = objectMapper.readValue(response, Key.class);
			String decodedName = personalDataEncryptor.decryptData(memberEntity.getName(),
				key);
			String decodedPhone = personalDataEncryptor.decryptData(memberEntity.getPhone(),
				key);
			return new MemberEntity(decodedName, decodedPhone);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
