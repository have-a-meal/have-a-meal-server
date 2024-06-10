package team.gwon.haveameal.unit.logging;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class LogFilterTestDto {
	private String userId;
	private String password;
	private String name;
}
