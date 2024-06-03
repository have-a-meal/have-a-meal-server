package team.gwon.haveameal.payment.dto;

import lombok.Getter;

@Getter
public class PaymentVerifyRequestDto {
	private byte[] paymentId;
	private String impUid;
	// private PortOnePaymentStatus message;
}
