package team.gwon.haveameal.excelextract.component;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import team.gwon.haveameal.excelextract.entity.Food;
import team.gwon.haveameal.excelextract.entity.Meal;
import team.gwon.haveameal.excelextract.service.FoodService;
import team.gwon.haveameal.excelextract.service.MealService;
import team.gwon.haveameal.excelextract.service.MenuService;

@RequiredArgsConstructor
@Component
@Slf4j
public class MenuFacade {

	private final MealService mealService;
	private final FoodService foodService;
	private final MenuService menuService;
	private final ExtractData extractData;

	//return type 추후에 바꿀 예정
	public void uploadExcel(MultipartFile multipartFile) throws Exception {
		List<Map<String, Object>> data = extractData.extract(multipartFile);
		List<Meal> meals = mealService.getMeals(data);
		List<Food> foods = foodService.getFoods(data);
		List<Integer> foodLength = foodService.getLength();
		menuService.insertMenu(meals, foods, foodLength);
		foodService.removeFoodLength();
	}
}
