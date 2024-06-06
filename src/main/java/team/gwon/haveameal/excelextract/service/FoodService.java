package team.gwon.haveameal.excelextract.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import team.gwon.haveameal.excelextract.entity.Food;
import team.gwon.haveameal.excelextract.mapper.ExcelMapper;

@Service
@RequiredArgsConstructor
public class FoodService {

	private final ExcelMapper excelMapper;
	@Getter
	private final List<Integer> foodLength = new ArrayList<>();

	public List<Food> getFoods(List<Map<String, Object>> data) {
		List<Food> foods = new ArrayList<>();
		for (Map<String, Object> map : data) {
			if (map.get("meal").equals("")) {
				continue;
			}
			Food food;
			int length = 0;
			for (String foodList : map.get("meal").toString().replaceAll("[\\[|\\]\\s]", "").split(",")) {
				food = new Food(foodList);
				foods.add(food);
				length++;
			}
			foodLength.add(length);
		}
		excelMapper.bulkInsertFood(foods);
		return foods;
	}

	public void removeFoodLength() {
		foodLength.clear();
	}

}
