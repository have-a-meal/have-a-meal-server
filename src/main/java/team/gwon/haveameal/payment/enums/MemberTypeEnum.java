package team.gwon.haveameal.payment.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MemberTypeEnum {
	INSIDER("내부인"),
	OUTSIDER("외부인");

	private final String memberType;
}
