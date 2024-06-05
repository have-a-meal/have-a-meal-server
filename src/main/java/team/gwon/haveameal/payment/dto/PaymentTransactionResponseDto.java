package team.gwon.haveameal.payment.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PaymentTransactionResponseDto {
	private byte[] paymentId;
	private int courseId;
	private String status;
	private LocalDateTime accountDate;
	private LocalDateTime refundDate;
	private int price;

	public static PaymentTransactionResponseDto fromPaymentWithCourseIncludeDetail(
		PaymentWithCourseIncludeDetail paymentWithCourseIncludeDetail) {
		return PaymentTransactionResponseDto.builder()
			.paymentId(paymentWithCourseIncludeDetail.getPayment().getPaymentId())
			.courseId(paymentWithCourseIncludeDetail.getCourse().getCourseId())
			.timing(paymentWithCourseIncludeDetail.getCourse().getTiming())
			.courseType(paymentWithCourseIncludeDetail.getCourse().getCourseType())
			.status(paymentWithCourseIncludeDetail.getPaymentDetail().getStatus())
			.accountDate(paymentWithCourseIncludeDetail.getPaymentDetail().getPaidAt())
			.price(paymentWithCourseIncludeDetail.getCourseDetail().getPrice()).build();
	}
}
