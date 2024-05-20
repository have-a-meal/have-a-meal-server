package team.gwon.haveameal.payment.entity;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CourseDetail {
	int courseDetailId;
	String memberType;
	int price;
}
