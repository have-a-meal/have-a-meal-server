package team.gwon.haveameal.excelextract.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import team.gwon.haveameal.excelextract.entity.Meal;
import team.gwon.haveameal.excelextract.mapper.ExcelMapper;

@Service
@RequiredArgsConstructor
public class MealService {

	private final ExcelMapper excelMapper;

	public List<Meal> getMeals(List<Map<String, Object>> data) {
		List<Meal> meals = new ArrayList<>();
		for (Map<String, Object> map : data) {
			if (map.get("meal").equals("")) {
				continue;
			}
			int courseId = excelMapper.selectCourseId(map);
			Date date = (Date)map.get("date");
			Meal meal = new Meal(courseId, date);
			excelMapper.insertMeal(meal);
			meals.add(meal);
		}
		return meals;
	}
}
