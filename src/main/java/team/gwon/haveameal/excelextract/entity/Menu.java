package team.gwon.haveameal.excelextract.entity;

import lombok.Getter;

@Getter
public class Menu {
	private long mealId;
	private String foodName;
	private int isMain;

	public Menu() {
	}

	public Menu(long mealId, String foodName, int isMain) {
		this.mealId = mealId;
		this.foodName = foodName;
		this.isMain = isMain;
	}
}
