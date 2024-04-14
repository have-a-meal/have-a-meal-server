package team.gwon.haveameal.payment.entity;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class Payment {
	byte[] paymentId;
	String memberId;
	int courseId;
	String pgProvider;
	String payMethod;
	LocalDateTime resquestAt;
}
