package team.gwon.haveameal.payment.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import team.gwon.haveameal.common.util.UuidProvider;
import team.gwon.haveameal.payment.entity.PaymentWithCourseIncludeDetail;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PaymentTransactionResponseDto {
	private String paymentId;
	private int courseId;
	private String timing;
	private String courseType;
	private String status;
	private LocalDateTime accountDate;
	private int price;

	public static PaymentTransactionResponseDto fromPaymentWithCourseIncludeDetail(
		PaymentWithCourseIncludeDetail paymentWithCourseIncludeDetail) {
		return PaymentTransactionResponseDto.builder()
			.paymentId(UuidProvider.byteToString(paymentWithCourseIncludeDetail.getPayment().getPaymentId()))
			.courseId(paymentWithCourseIncludeDetail.getCourse().getCourseId())
			.timing(paymentWithCourseIncludeDetail.getCourse().getTiming())
			.courseType(paymentWithCourseIncludeDetail.getCourse().getCourseType())
			.status(paymentWithCourseIncludeDetail.getPaymentDetail().getStatus())
			.accountDate(paymentWithCourseIncludeDetail.getPaymentDetail().getPaidAt())
			.price(paymentWithCourseIncludeDetail.getCourseDetail().getPrice()).build();
	}
}
