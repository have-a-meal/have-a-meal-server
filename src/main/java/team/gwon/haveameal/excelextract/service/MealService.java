package team.gwon.haveameal.excelextract.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import team.gwon.haveameal.excelextract.entity.Meal;
import team.gwon.haveameal.excelextract.error.CustomException;
import team.gwon.haveameal.excelextract.error.ErrorCode;
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
			Optional<Integer> courseId = excelMapper.selectCourseId(map);
			Date date = (Date)map.get("date");
			if (courseId.isEmpty()) {
				throw new CustomException(ErrorCode.INCORRECT_COURSE_ARGUMENT);
			}
			Meal meal = new Meal(courseId.get(), date);
			excelMapper.selectMeal(meal).ifPresent(m -> {
				throw new CustomException(ErrorCode.DUPLICATED_EXCEL_FILE);
			});
			int insertRowCnt = excelMapper.insertMeal(meal);
			if (insertRowCnt > 0) {
				meals.add(meal);
			} else {
				throw new CustomException(ErrorCode.FAILED_INSERT);
			}
		}
		return meals;
	}

	public List<Meal> updateMeals(List<Map<String, Object>> data) {
		Date firstDate = (Date)data.get(0).get("date");
		Date lastDate = (Date)data.get(data.size() - 1).get("date");
		Map<String, Date> dateMap = new HashMap<>();
		dateMap.put("firstDate", firstDate);
		dateMap.put("lastDate", lastDate);
		int deleteRowCnt = excelMapper.deleteMeal(dateMap);
		if (deleteRowCnt > 0) {
			return getMeals(data);
		} else {
			throw new CustomException(ErrorCode.FAILED_DELETE);
		}
	}
}
