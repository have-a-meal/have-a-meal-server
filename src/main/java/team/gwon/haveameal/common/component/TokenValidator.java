package team.gwon.haveameal.common.component;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class TokenValidator implements ConstraintValidator<TokenCheck, String> {
	private final TokenProvider tokenProvider;

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return tokenProvider.isValidToken(value);
	}
}
