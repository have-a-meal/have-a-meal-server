package team.gwon.haveameal.payment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import team.gwon.haveameal.payment.entity.Course;
import team.gwon.haveameal.payment.entity.CourseDetail;
import team.gwon.haveameal.payment.entity.CourseWithDetail;

@Getter
@AllArgsConstructor
@ToString
public class TicketPriceRequestDto {
	private String timing;
	private String courseType;
	private String memberType;

	public CourseWithDetail toCourseWithDetail() {
		Course course = Course.builder()
			.timing(this.timing)
			.courseType(this.courseType).build();
		CourseDetail courseDetail = CourseDetail.builder()
			.memberType(this.memberType).build();
		return CourseWithDetail.builder().course(course).courseDetail(courseDetail).build();

	}
}
