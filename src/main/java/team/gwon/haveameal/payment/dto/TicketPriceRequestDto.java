package team.gwon.haveameal.payment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
	@NotBlank(message = "조식, 중식, 석식 중 하나의 값이어야 합니다.")
	@Pattern(regexp = "[조중석][식]", message = "조식, 중식, 석식 중 하나의 값이어야 합니다.")
	private String timing;

	@NotBlank(message = "A, B, C 타입 중 하나여야합니다.")
	@Pattern(regexp = "[A-C]", message = "A, B, C 타입 중 하나여야합니다.")
	private String courseType;

	@NotBlank
	@Size(min = 7, max = 8, message = "내부인은 8자, 외부인은 7자의 값이어야 합니다.")
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
