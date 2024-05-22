package team.gwon.haveameal.payment.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.ToString;
import team.gwon.haveameal.common.util.UuidProvider;
import team.gwon.haveameal.payment.entity.Payment;

@Getter
@ToString
public class TicketBuyRequestDto {
	private String memberId;
	private int courseId;
	private String pgProvider; //"kakaopay" or "tosspay"
	private String payMethod; //"kakaopay" or "tosspay"

	public Payment toPayment() {
		byte[] paymentId = UuidProvider.stringToByte(UuidProvider.generateSequentialUuid());
		return Payment.builder()
			.paymentId(paymentId)
			.memberId(this.memberId)
			.courseId(this.courseId)
			.pgProvider(this.pgProvider)
			.payMethod(this.payMethod)
			.requestAt(LocalDateTime.now()).build();
	}
}
