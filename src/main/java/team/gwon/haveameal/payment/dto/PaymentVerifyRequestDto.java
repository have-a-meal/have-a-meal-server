package team.gwon.haveameal.payment.dto;

import lombok.Getter;

@Getter
public class PaymentVerifyRequestDto {
	private byte[] payment_id;
	private String impUid;
	// private PortOnePaymentStatus message;
}
