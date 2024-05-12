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
			Meal meal = new Meal(courseId.orElseThrow(), date);
			//orElseThrow에서 예외의 종류를 생성해서 알아보기 쉽게하는게 좋을듯.
			//예외 지정안해주면 NoSuchElementException발생
			//지정 시 IllegalAccessError::new와 같이 서플라이?로 생성
			//date는 맞지않는 형식으로 입력이 들어오면 데이터 삽입 자체가 안되는거 같음.
			excelMapper.selectMeal(meal).ifPresent(m -> {
				throw new RuntimeException("duplicate excel data");
			});
			//exception 처리 고민. custom해서 공통으로 사용하도록 하는지 아님 개별적으로 만들어서 사용하는지
			//일단 runtimeException으로만 두고 넘어가는걸로.
			excelMapper.insertMeal(meal);
			meals.add(meal);
		}
		return meals;
	}

	public List<Meal> updateMeals(List<Map<String, Object>> data) {
		Date firstDate = (Date)data.get(0).get("date");
		Date lastDate = (Date)data.get(data.size() - 1).get("date");
		Map<String, Date> dateMap = new HashMap<>();
		dateMap.put("firstDate", firstDate);
		dateMap.put("lastDate", lastDate);
		excelMapper.deleteMeal(dateMap);
		return getMeals(data);
	}

	// public List<Meal> getAllMeals() {
	// 	return excelMapper.selectAllMeal();
	// }
}
