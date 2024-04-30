package team.gwon.haveameal.excelextract.entity;

import lombok.Getter;

@Getter
public class Menu {
	private long mealId;
	private long foodId;
	private int isMain;

	public Menu() {
	}

	public Menu(long mealId, long foodId, int isMain) {
		this.mealId = mealId;
		this.foodId = foodId;
		this.isMain = isMain;
	}
}
