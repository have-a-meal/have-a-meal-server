package team.gwon.haveameal.ticket.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import team.gwon.haveameal.payment.entity.Course;
import team.gwon.haveameal.payment.entity.PaymentWithCourseIncludeDetail;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TicketFindResponseDto {
	private String timing;
	private String courseType;
	private int quantity;

	public static TicketFindResponseDto of(PaymentWithCourseIncludeDetail payment) {
		Course course = payment.getCourse();
		return new TicketFindResponseDto(course.getTiming(), course.getCourseType(), payment.getQuantity());
	}
}
