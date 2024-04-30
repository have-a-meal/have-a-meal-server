package team.gwon.haveameal.excelextract.service;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import team.gwon.haveameal.excelextract.component.ExtractData;
import team.gwon.haveameal.excelextract.entity.Food;
import team.gwon.haveameal.excelextract.entity.Meal;
import team.gwon.haveameal.excelextract.entity.Relation;
import team.gwon.haveameal.excelextract.mapper.ExcelMapper;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExcelExtractService {

	private final ExtractData extractData;
	private final ExcelMapper excelMapper;

	public List<Map<String, Object>> excelUpload(MultipartFile multipartFile) throws Exception {
		List<Map<String, Object>> data = extractData.extract(multipartFile);
		// List<Food> foodList = new ArrayList<>();
		// List<Meal> mealList = new ArrayList<>();
		// List<Relation> relationList = new ArrayList<>();
		for (Map<String, Object> map : data) {
			if (map.get("meal").equals("")) {
				continue;
			}
			int courseId = excelMapper.selectCourseId(map);
			Date date = (Date)map.get("date");
			Meal meal = new Meal(courseId, date);
			excelMapper.insertMeal(meal);
			// mealList.add(meal);
			Food food;
			Relation relation;
			int index = 0;
			for (String foods : map.get("meal").toString().replaceAll("[\\[|\\]\\s]", "").split(",")) {
				Optional<Food> existFood = excelMapper.selectFood(foods);
				if (existFood.isPresent()) {
					food = existFood.get();
					// foodList.add(food);
				} else {
					food = new Food(foods);
					excelMapper.insertFood(food);
					// foodList.add(food);
				}
				if (index == 0) {
					relation = new Relation(meal.getMealId(), food.getFoodId(), 1);
					excelMapper.insertRelation(relation);
					// relationList.add(relation);
				} else {
					relation = new Relation(meal.getMealId(), food.getFoodId(), 0);
					excelMapper.insertRelation(relation);
					// relationList.add(relation);
				}
				index++;
			}
		}
		// excelMapper.bulkInsertMeal(mealList);
		// excelMapper.bulkInsertFood(foodList);
		// excelMapper.bulkInsertRelation(relationList);
		return data;
	}
}
