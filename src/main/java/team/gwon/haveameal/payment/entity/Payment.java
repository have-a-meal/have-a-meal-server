package team.gwon.haveameal.payment.entity;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Payment {
	byte[] paymentId;
	String memberId;
	int courseId;
	String pgProvider;
	String payMethod;
	LocalDateTime requestAt;
}
