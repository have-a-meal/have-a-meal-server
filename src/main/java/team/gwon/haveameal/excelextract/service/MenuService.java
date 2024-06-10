package team.gwon.haveameal.excelextract.service;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import team.gwon.haveameal.excelextract.entity.Food;
import team.gwon.haveameal.excelextract.entity.Meal;
import team.gwon.haveameal.excelextract.entity.Menu;
import team.gwon.haveameal.excelextract.error.CustomException;
import team.gwon.haveameal.excelextract.error.ErrorCode;
import team.gwon.haveameal.excelextract.mapper.ExcelMapper;

@RequiredArgsConstructor
@Service
public class MenuService {
	private final ExcelMapper excelMapper;

	public void insertMenu(List<Meal> meals, List<Food> foods, List<Integer> foodLength) {
		int index = 0;
		boolean flag;
		for (int l = 0; l < foodLength.size(); l++) {
			flag = true;
			int limit = foodLength.get(l) + index;
			for (; index < limit; index++) {
				Menu menu;
				if (flag) {
					menu = new Menu(meals.get(l), foods.get(index), 1);
					flag = false;
				} else {
					menu = new Menu(meals.get(l), foods.get(index), 0);
				}
				int insertRowCnt = excelMapper.insertMenu(menu);
				if (insertRowCnt <= 0) {
					throw new CustomException(ErrorCode.FAILED_INSERT);
				}

			}
		}
	}

	public void updateAfterDelteMenu(List<Meal> meals, List<Food> foods, List<Integer> foodLength) {
		Map<String, Date> dateMap = new HashMap<>();
		Date firstDate = meals.get(0).getDate();
		Date lastDate = meals.get(meals.size() - 1).getDate();
		dateMap.put("firstDate", firstDate);
		dateMap.put("lastDate", lastDate);
		int deleteRowCnt = excelMapper.deleteMenu(dateMap);
		if (deleteRowCnt > 0) {
			insertMenu(meals, foods, foodLength);
		} else {
			throw new CustomException(ErrorCode.FAILED_DELETE);
		}
	}
}
