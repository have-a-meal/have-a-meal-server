package team.gwon.haveameal.payment.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CourseDetail {
	int courseDetailId;
	String memberType;
	int price;
}
