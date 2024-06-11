package team.gwon.haveameal.member.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Key {
	/*
		iv = Initialization Vector
	 */
	private byte[] encryptionKey;
	private byte[] iv;
}
