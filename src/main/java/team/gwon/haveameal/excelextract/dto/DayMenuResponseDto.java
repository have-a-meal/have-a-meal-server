package team.gwon.haveameal.excelextract.dto;

import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import team.gwon.haveameal.payment.entity.Course;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class DayMenuResponseDto {
	private String timing;
	private String courseType;
	private String main;
	private List<String> sub;

	public static DayMenuResponseDto from(Course course, List<String> foods) {
		String firstFood = foods.get(0);
		foods.remove(0);
		return new DayMenuResponseDto(course.getTiming(), course.getCourseType(), firstFood, foods);
	}
}
