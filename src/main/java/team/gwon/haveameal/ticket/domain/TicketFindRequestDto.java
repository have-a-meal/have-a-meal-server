package team.gwon.haveameal.ticket.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import team.gwon.haveameal.member.domain.MemberEntity;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TicketFindRequestDto {
	private String memberId;

	public static TicketFindRequestDto from(String memberId) {
		return new TicketFindRequestDto(memberId);
	}

	public static MemberEntity from(TicketFindRequestDto ticketFindRequestDto) {
		return MemberEntity.builder().memberId(ticketFindRequestDto.getMemberId()).build();
	}
}
