package team.gwon.haveameal.excelextract.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import team.gwon.haveameal.excelextract.dto.DayMenuResponseDto;
import team.gwon.haveameal.excelextract.service.DayMenuService;

@RequiredArgsConstructor
@RestController
public class MenuController {

	private final DayMenuService dayMenuService;

	@GetMapping("/Menu/{date}")
	public ResponseEntity<List<DayMenuResponseDto>> getDayMenu(@PathVariable String date) {
		return ResponseEntity.status(HttpStatus.OK).body(dayMenuService.getDayMenu(date));
	}
}
