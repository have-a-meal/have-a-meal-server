package team.gwon.haveameal.excelextract.entity;

import lombok.Getter;

@Getter
public class Food {
	private long foodId;
	private String name;

	public Food() {
	}

	public Food(String name) {
		this.name = name;
	}
}
