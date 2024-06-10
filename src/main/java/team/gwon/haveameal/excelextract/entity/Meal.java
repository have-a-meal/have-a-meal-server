package team.gwon.haveameal.excelextract.entity;

import java.sql.Date;

import lombok.Getter;

@Getter
public class Meal {
	private long mealId;
	private Date date;
	private int courseId;

	public Meal() {
	}

	public Meal(int courseId, Date date) {
		this.courseId = courseId;
		this.date = date;
	}
}
