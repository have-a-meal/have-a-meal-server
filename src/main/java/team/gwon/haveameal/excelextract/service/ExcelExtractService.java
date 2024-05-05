package team.gwon.haveameal.excelextract.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import team.gwon.haveameal.excelextract.component.ExtractData;
import team.gwon.haveameal.excelextract.entity.Food;
import team.gwon.haveameal.excelextract.entity.Meal;
import team.gwon.haveameal.excelextract.entity.Menu;
import team.gwon.haveameal.excelextract.mapper.ExcelMapper;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExcelExtractService {

	private final ExtractData extractData;
	private final ExcelMapper excelMapper;

	@Transactional
	public List<Map<String, Object>> excelUpload(MultipartFile multipartFile) throws Exception {
		List<Map<String, Object>> data = extractData.extract(multipartFile);
		List<Food> foodList = new ArrayList<>();
		List<Meal> mealList = new ArrayList<>();
		List<Integer> foodLength = new ArrayList<>();
		for (Map<String, Object> map : data) {
			if (map.get("meal").equals("")) {
				continue;
			}
			int courseId = excelMapper.selectCourseId(map);
			Date date = (Date)map.get("date");
			Meal meal = new Meal(courseId, date);
			excelMapper.insertMeal(meal);
			mealList.add(meal);
			Food food;
			int length = 0;
			for (String foods : map.get("meal").toString().replaceAll("[\\[|\\]\\s]", "").split(",")) {
				food = new Food(foods);
				foodList.add(food);
				length++;
			}
			foodLength.add(length);
		}
		excelMapper.bulkInsertFood(foodList);
		log.info(foodLength.size() + "//" + mealList.size() + "//" + foodList.size());
		int index = 0;
		boolean flag;
		for (int l = 0; l < foodLength.size(); l++) {
			flag = true;
			int limit = foodLength.get(l) + index;
			while (index++ < limit) {
				Menu menu;
				if (flag) {
					menu = new Menu(mealList.get(l).getMealId(), foodList.get(index).getName(), 1);
					flag = false;
				} else {
					menu = new Menu(mealList.get(l).getMealId(), foodList.get(index).getName(), 0);
				}
				excelMapper.insertMenu(menu);
			}
		}
		return data;
	}
}
