package team.gwon.haveameal.payment.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
	byte[] paymentId;
	String memberId;
	int courseId;
	String pgProvider;
	String payMethod;
	LocalDateTime requestAt;
}
