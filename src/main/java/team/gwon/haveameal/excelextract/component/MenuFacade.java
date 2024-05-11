package team.gwon.haveameal.excelextract.component;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import team.gwon.haveameal.excelextract.entity.Food;
import team.gwon.haveameal.excelextract.entity.Meal;
import team.gwon.haveameal.excelextract.service.FoodService;
import team.gwon.haveameal.excelextract.service.MealService;
import team.gwon.haveameal.excelextract.service.MenuService;

@RequiredArgsConstructor
@Component
public class MenuFacade {

	private final MealService mealService;
	private final FoodService foodService;
	private final MenuService menuService;
	private final ExtractData extractData;

	@Transactional
	//return type 추후에 바꿀 예정
	public void uploadExcel(MultipartFile multipartFile) throws Exception {
		List<Map<String, Object>> data = extractData.extract(multipartFile);
		List<Meal> meals = mealService.getMeals(data);
		List<Food> foods = foodService.getFoods(data);
		List<Integer> foodLength = foodService.getFoodLength();
		menuService.insertMenu(meals, foods, foodLength);
		foodService.removeFoodLength();
	}

	// public void updateExcel(MultipartFile multipartFile) throws Exception {
	// 	List<Map<String, Object>> data = extractData.extract(multipartFile);
	// 	List<Meal> meals = mealService.getAllMeals();
	// 	List<Food> newFoods = foodService.getFoods(data);
	// 	List<Integer> newFoodLength = foodService.getFoodLength();
	// 	List<Food> oldFoods = foodService.getAllOldFoods();
	// 	List<Integer> oldFoodLength = foodService.getOldFoodLength();
	// 	menuService.updateMenu(meals, newFoods, newFoodLength, oldFoods, oldFoodLength);
	// }

	public void updateAfterDeleteExcel(MultipartFile multipartFile) throws Exception {
		List<Map<String, Object>> data = extractData.extract(multipartFile);
		List<Meal> meals = mealService.updateMeals(data);
		List<Food> foods = foodService.getFoods(data);
		List<Integer> foodLength = foodService.getFoodLength();
		menuService.updateAfterDelteMenu(meals, foods, foodLength);
		foodService.removeFoodLength();
	}
}
