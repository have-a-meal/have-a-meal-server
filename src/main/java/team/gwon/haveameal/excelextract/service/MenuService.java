package team.gwon.haveameal.excelextract.service;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	// public void updateMenu(List<Meal> meals, List<Food> newFoods,
	// 	List<Integer> newFoodLength, List<Food> oldFoods, List<Integer> oldFoodLength) {
	// 	for (Meal meal : meals) {
	// 		for (int i = 0; i < newFoods.size(); i++) {
	// 			//newFoodLength랑 oldFoodLength랑 사이즈가 다를땐 어떻게 할건데.
	// 			//list에 contains 메소드 써서 newfoods값이 old에 있으면 패스 없으면
	// 			//newfoods로 전면 수정하는 방안이 맞을거 같은데
	// 			//그렇다면 수정하고 old의 갯수가 더 적거나 많을 경우를 고려하면 될거 같음.
	// 		}
	// 	}
	// }

	public void updateAfterDelteMenu(List<Meal> meals, List<Food> foods, List<Integer> foodLength) {
		Map<String, Date> dateMap = new HashMap<>();
		Date firstDate = meals.get(0).getDate();
		Date lastDate = meals.get(meals.size() - 1).getDate();
		dateMap.put("firstDate", firstDate);
		dateMap.put("lastDate", lastDate);
		excelMapper.deleteMenu(dateMap);
		insertMenu(meals, foods, foodLength);
	}
}
