package team.gwon.haveameal.payment.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseDetail {
	int courseDetailId;
	String memberType;
	int price;
}
