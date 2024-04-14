package team.gwon.haveameal.payment.entity;

import lombok.Getter;

@Getter
public class PaymentWithDetailIncludeRefund {
	Payment payment;
	PaymentDetail paymentDetail;
	RefundReason refundReason;
}
