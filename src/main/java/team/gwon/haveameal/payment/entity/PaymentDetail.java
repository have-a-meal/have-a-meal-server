package team.gwon.haveameal.payment.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDetail {
	byte[] paymentDetailId;
	int refundReasonId;
	String status;
	String impUid;
	String pgTid;
	LocalDateTime paidAt;
}
