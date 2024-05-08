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
			excelMapper.selectMeal(meal).ifPresent(m -> {
				throw new RuntimeException("duplicate meal");
			});
			//exception 처리 고민. custom해서 공통으로 사용하도록 하는지 아님 개별적으로 만들어서 사용하는지
			//일단 runtimeException으로만 두고 넘어가는걸로.
			excelMapper.insertMeal(meal);
			meals.add(meal);
		}
		return meals;
	}
}
