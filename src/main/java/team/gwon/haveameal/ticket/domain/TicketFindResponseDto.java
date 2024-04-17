package team.gwon.haveameal.ticket.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import team.gwon.haveameal.payment.entity.Course;
import team.gwon.haveameal.payment.entity.PaymentWithCourseIncludeDetail;
import team.gwon.haveameal.payment.entity.TicketQuantity;

@ToString
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TicketFindResponseDto {
	private String timing;
	private String courseType;
	private int quantity;

	public static TicketFindResponseDto from(PaymentWithCourseIncludeDetail payment) {
		Course course = payment.getCourse();
		TicketQuantity ticketQuantity = payment.getTicketQuantity();
		return new TicketFindResponseDto(course.getTiming(), course.getCourseType(), ticketQuantity.getQuantity());
	}

}
