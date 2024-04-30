package team.gwon.haveameal.excelextract.mapper;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import team.gwon.haveameal.excelextract.entity.Food;
import team.gwon.haveameal.excelextract.entity.Meal;
import team.gwon.haveameal.excelextract.entity.Menu;

@Mapper
public interface ExcelMapper {

	int selectCourseId(Map<String, Object> param);

	Optional<Food> selectFood(String name);

	void insertMeal(Meal meal);

	void insertFood(Food food);

	void insertRelation(Menu menu);

	void bulkInsertMeal(List<Meal> meals);

	void bulkInsertFood(List<Food> foods);

	void bulkInsertRelation(List<Menu> menus);
}
