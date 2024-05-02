package team.gwon.haveameal.payment.entity;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Course {
	int courseId;
	String timing;
	String courseType;
	int courseDetailId;
}
