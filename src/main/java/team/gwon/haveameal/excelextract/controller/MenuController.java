package team.gwon.haveameal.excelextract.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import team.gwon.haveameal.common.component.swagger.SwaggerApiBadRequest;
import team.gwon.haveameal.common.component.swagger.SwaggerApiSuccess;
import team.gwon.haveameal.excelextract.dto.DayMenuResponseDto;
import team.gwon.haveameal.excelextract.error.CustomException;
import team.gwon.haveameal.excelextract.error.ErrorCode;
import team.gwon.haveameal.excelextract.service.DayMenuService;

@RequiredArgsConstructor
@RestController
public class MenuController {

	private final DayMenuService dayMenuService;

	@SwaggerApiSuccess(summary = "메뉴 조회", implementation = DayMenuResponseDto.class)
	@SwaggerApiBadRequest
	@GetMapping(value = {"/menu/{date}", "/menu"})
	public ResponseEntity<List<DayMenuResponseDto>> getDayMenu(@PathVariable(required = false) Optional<String> date) {
		if (date.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(dayMenuService.getDayMenu(date.get()));
		}
		throw new CustomException(ErrorCode.EMPTY_DATE);
	}
}
