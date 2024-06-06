package team.gwon.haveameal.excelextract.mapper;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import team.gwon.haveameal.excelextract.entity.Food;
import team.gwon.haveameal.excelextract.entity.Meal;
import team.gwon.haveameal.excelextract.entity.Menu;
import team.gwon.haveameal.payment.entity.Course;

@Mapper
public interface ExcelMapper {

	Optional<Integer> selectCourseId(Map<String, Object> param);

	int insertMeal(Meal meal);

	int insertMenu(Menu menu);

	int bulkInsertFood(List<Food> foods);

	Optional<Meal> selectMeal(Meal meal);

	List<Integer> selectDayCourseId(String date);

	List<String> selectDayFood(Meal meal);

	Course selectDayCourse(int courseId);

	int deleteMeal(Map<String, Date> dateMap);

	int deleteMenu(Map<String, Date> dateMap);

}
