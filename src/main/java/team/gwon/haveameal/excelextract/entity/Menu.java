package team.gwon.haveameal.excelextract.entity;

import lombok.Getter;

@Getter
public class Menu {
	private Meal meal;
	private Food food;
	private int isMain;

	public Menu() {
	}

	public Menu(Meal meal, Food food, int isMain) {
		this.meal = meal;
		this.food = food;
		this.isMain = isMain;
	}
}
