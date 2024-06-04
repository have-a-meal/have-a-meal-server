package team.gwon.haveameal.payment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import team.gwon.haveameal.payment.entity.Course;
import team.gwon.haveameal.payment.entity.CourseDetail;
import team.gwon.haveameal.payment.entity.CourseWithDetail;
import team.gwon.haveameal.payment.enums.MemberTypeEnum;

@Getter
@AllArgsConstructor
@ToString
public class TicketPriceRequestDto {
	private String timing;
	private String courseType;
	private String memberId;

	public CourseWithDetail toCourseWithDetail() {
		Course course = Course.builder()
			.timing(this.timing)
			.courseType(this.courseType).build();
		CourseDetail courseDetail = CourseDetail.builder()
			.memberType(convertMemberType(memberId)).build();
		return CourseWithDetail.builder().course(course).courseDetail(courseDetail).build();
	}

	public String convertMemberType(String memberId) {
		return (memberId.length() == 8) ? MemberTypeEnum.INSIDER.getMemberType() :
			MemberTypeEnum.OUTSIDER.getMemberType();
	}
}
