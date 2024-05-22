package team.gwon.haveameal.payment.entity;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class PaymentDetail {
	byte[] paymentDetailId;
	int refundReasonId;
	String status;
	String impUid;
	String pgTid;
	LocalDateTime paidAt;
}
