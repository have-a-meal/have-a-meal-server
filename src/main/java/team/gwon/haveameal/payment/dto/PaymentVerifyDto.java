package team.gwon.haveameal.payment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import team.gwon.haveameal.payment.enums.PortOnePaymentStatus;

@Getter
@AllArgsConstructor
public class PaymentVerifyDto {
	private String impUid;
	private String name;
	private int courseId;
	private PortOnePaymentStatus message;
	private Long amount;
}
