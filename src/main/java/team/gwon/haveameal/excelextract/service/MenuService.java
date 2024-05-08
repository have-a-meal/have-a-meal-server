package team.gwon.haveameal.excelextract.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import team.gwon.haveameal.excelextract.entity.Food;
import team.gwon.haveameal.excelextract.entity.Meal;
import team.gwon.haveameal.excelextract.entity.Menu;
import team.gwon.haveameal.excelextract.mapper.ExcelMapper;

@RequiredArgsConstructor
@Service
public class MenuService {
	private final ExcelMapper excelMapper;

	public void insertMenu(List<Meal> meals, List<Food> foods, List<Integer> foodLength) {
		int index = 0;
		boolean flag;
		for (int l = 0; l < foodLength.size(); l++) {
			flag = true;
			int limit = foodLength.get(l) + index;
			for (; index < limit; index++) {
				Menu menu;
				if (flag) {
					menu = new Menu(meals.get(l), foods.get(index), 1);
					flag = false;
				} else {
					menu = new Menu(meals.get(l), foods.get(index), 0);
				}
				excelMapper.insertMenu(menu);
			}
		}
	}
}
