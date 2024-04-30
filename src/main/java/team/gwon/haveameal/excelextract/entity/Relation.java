package team.gwon.haveameal.excelextract.entity;

import lombok.Getter;

@Getter
public class Relation {
	private long mealId;
	private long foodId;
	private int isMain;

	public Relation() {
	}

	public Relation(long mealId, long foodId, int isMain) {
		this.mealId = mealId;
		this.foodId = foodId;
		this.isMain = isMain;
	}
}
