package team.gwon.haveameal.payment.enums;

import lombok.Getter;

@Getter
public enum PortOnePaymentStatus {
	READY("미결제"),
	PAID("결제 완료"),
	FAILED("결제 실패");
	private final String message;

	PortOnePaymentStatus(String message) {
		this.message = message;
	}
}
