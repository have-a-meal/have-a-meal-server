package team.gwon.haveameal.payment.entity;

import lombok.Getter;

@Getter
public class PaymentWithCourseIncludeDetail {
	Payment payment;
	PaymentDetail paymentDetail;
	Course course;
	CourseDetail courseDetail;
	TicketQuantity ticketQuantity;
}
