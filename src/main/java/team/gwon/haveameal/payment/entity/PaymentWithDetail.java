package team.gwon.haveameal.payment.entity;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PaymentWithDetail {
	Payment payment;
	PaymentDetail paymentDetail;
}
