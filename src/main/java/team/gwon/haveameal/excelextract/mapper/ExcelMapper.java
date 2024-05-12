package team.gwon.haveameal.excelextract.mapper;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import team.gwon.haveameal.excelextract.entity.Food;
import team.gwon.haveameal.excelextract.entity.Meal;
import team.gwon.haveameal.excelextract.entity.Menu;

@Mapper
public interface ExcelMapper {

	Optional<Integer> selectCourseId(Map<String, Object> param);

	void insertMeal(Meal meal);

	void insertMenu(Menu menu);

	void bulkInsertFood(List<Food> foods);

	Optional<Meal> selectMeal(Meal meal);

	// List<Food> selectAllFood();

	// List<Integer> selectFoodLength();

	// List<Meal> selectAllMeal();

	void deleteMeal(Map<String, Date> dateMap);

	void deleteMenu(Map<String, Date> dateMap);
}
