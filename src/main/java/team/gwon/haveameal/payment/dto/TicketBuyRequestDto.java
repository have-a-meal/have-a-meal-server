package team.gwon.haveameal.payment.dto;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.hibernate.validator.constraints.Range;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.ToString;
import team.gwon.haveameal.common.util.UuidProvider;
import team.gwon.haveameal.payment.entity.Payment;

@Getter
@ToString
public class TicketBuyRequestDto {
	@NotBlank(message = "memberId는 필수값입니다.")
	@Size(min = 7, max = 8, message = "내부인은 8자, 외부인은 7자의 값이어야 합니다.")
	private String memberId;

	@Range(min = 1, max = 18, message = "courseId는 1부터 18 사이의 숫자여야합니다.")
	private int courseId;

	@NotBlank(message = "pgProvider는 필수값입니다.")
	private String pgProvider; //"kakaopay" or "tosspay"

	@NotBlank(message = "payMethod는 필수값입니다.")
	private String payMethod; //"kakaopay" or "tosspay"

	public Payment toPayment() {
		byte[] paymentId = UuidProvider.stringToByte(UuidProvider.generateSequentialUuid());
		return Payment.builder()
			.paymentId(paymentId)
			.memberId(this.memberId)
			.courseId(this.courseId)
			.pgProvider(this.pgProvider)
			.payMethod(this.payMethod)
			.requestAt(getSeoulLocalDateTime()).build();
	}

	LocalDateTime getSeoulLocalDateTime() {
		return LocalDateTime.ofInstant(
			Instant.ofEpochMilli(System.currentTimeMillis()),
			ZoneId.of("Asia/Seoul"));
	}
}
