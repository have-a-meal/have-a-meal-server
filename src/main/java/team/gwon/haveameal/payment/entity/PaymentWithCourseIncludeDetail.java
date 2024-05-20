package team.gwon.haveameal.payment.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentWithCourseIncludeDetail {
	Payment payment;
	PaymentDetail paymentDetail;
	Course course;
	CourseDetail courseDetail;
	int quantity;
}
