package team.gwon.haveameal.excelextract.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import team.gwon.haveameal.excelextract.dto.DayMenuResponseDto;
import team.gwon.haveameal.excelextract.entity.Meal;
import team.gwon.haveameal.excelextract.mapper.ExcelMapper;
import team.gwon.haveameal.payment.entity.Course;

@RequiredArgsConstructor
@Service
@Slf4j
public class DayMenuService {

	private final ExcelMapper excelMapper;

	public List<DayMenuResponseDto> getDayMenu(String day) {
		Date date = Date.valueOf(day);
		List<DayMenuResponseDto> responseDtoList = new ArrayList<>();
		List<Integer> courseIdList = excelMapper.selectDayCourseId(day);
		for (int courseId : courseIdList) {
			Course course = excelMapper.selectDayCourse(courseId);
			Meal meal = new Meal(courseId, date);
			Optional<Meal> optionalMeal = excelMapper.selectMeal(meal);
			if (optionalMeal.isEmpty()) {
				return responseDtoList;
			}
			List<String> foods = excelMapper.selectDayFood(optionalMeal.get());
			responseDtoList.add(DayMenuResponseDto.from(course, foods));
		}
		for (DayMenuResponseDto dto : responseDtoList) {
			log.info(dto.toString());
		}
		return responseDtoList;
	}
}

