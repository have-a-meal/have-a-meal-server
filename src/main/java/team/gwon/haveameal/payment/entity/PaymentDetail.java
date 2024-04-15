package team.gwon.haveameal.payment.entity;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class PaymentDetail {
	byte[] paymentDetailId;
	int refundReasonId;
	String status;
	String impUid;
	String pgTid;
	LocalDateTime paidAt;
}
