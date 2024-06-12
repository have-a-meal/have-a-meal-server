package team.gwon.haveameal.ticket.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class QrContent {
	Long ticketId;
	String memberId;

	public static QrContent of(Long ticketId, String memberId) {
		return new QrContent(ticketId, memberId);
	}
}
