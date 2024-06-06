package team.gwon.haveameal.payment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import team.gwon.haveameal.payment.entity.CourseWithDetail;

@Getter
@AllArgsConstructor
@ToString
public class TicketPriceResponseDto {
	int courseId;
	int price;

	public static TicketPriceResponseDto fromTicketPrice(CourseWithDetail courseWithDetail) {
		return new TicketPriceResponseDto(courseWithDetail.getCourse().getCourseId(),
			courseWithDetail.getCourseDetail().getPrice());
	}
}
