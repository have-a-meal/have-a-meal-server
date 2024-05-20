package team.gwon.haveameal.payment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class TicketPriceRequestDto {
	private String timing;
	private String courseType;
	private String memberType;

}
