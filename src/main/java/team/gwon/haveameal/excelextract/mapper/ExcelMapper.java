package team.gwon.haveameal.excelextract.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import team.gwon.haveameal.excelextract.entity.Food;
import team.gwon.haveameal.excelextract.entity.Meal;
import team.gwon.haveameal.excelextract.entity.Menu;

@Mapper
public interface ExcelMapper {

	int selectCourseId(Map<String, Object> param);

	void insertMeal(Meal meal);

	void insertMenu(Menu menu);

	void bulkInsertFood(List<Food> foods);
}
