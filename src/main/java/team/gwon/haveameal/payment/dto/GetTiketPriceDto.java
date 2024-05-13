package team.gwon.haveameal.payment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetTiketPriceDto {
	String timing;
	String courseType;
	String memberType;
}
