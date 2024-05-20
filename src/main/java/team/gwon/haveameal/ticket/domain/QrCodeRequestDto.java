package team.gwon.haveameal.ticket.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import team.gwon.haveameal.payment.entity.Course;
import team.gwon.haveameal.payment.entity.Payment;
import team.gwon.haveameal.payment.entity.PaymentWithCourseIncludeDetail;

@Getter
@AllArgsConstructor
public class QrCodeRequestDto {
	private String memberId;
	private String courseType;
	private String timing;
	private int width;
	private int height;

	public static QrCodeRequestDto of(String memberId, String courseType, String timing, int width, int height) {
		return new QrCodeRequestDto(memberId, courseType, timing, width, height);
	}

	public PaymentWithCourseIncludeDetail toPaymentWithCourseIncludeDetail() {
		Payment payment = Payment.builder().memberId(this.memberId).build();
		Course course = Course.builder().courseType(courseType).timing(this.timing).build();
		return PaymentWithCourseIncludeDetail.builder().payment(payment).course(course).build();
	}
}
