package team.gwon.haveameal.payment.entity;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PaymentWithCourseIncludeDetail {
	Payment payment;
	PaymentDetail paymentDetail;
	Course course;
	CourseDetail courseDetail;
	int quantity;
}
