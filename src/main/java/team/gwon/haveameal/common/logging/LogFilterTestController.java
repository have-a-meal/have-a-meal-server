package team.gwon.haveameal.common.logging;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("test")
public class LogFilterTestController {
	@GetMapping("/logTest")
	public ResponseEntity<?> getLogTest() {
		return ResponseEntity.status(HttpStatus.OK).body("정상적으로 처리되었습니다.");
	}

	@PostMapping("/logTest")
	public ResponseEntity<?> postLogTest(@RequestBody LogFilterTestDto logFilterTestDto) {
		log.info("post 테스트 실행되었습니다.");
		log.info("logFilterTestDto : {}", logFilterTestDto);
		if (logFilterTestDto == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("memberRegisterDto가 없습니다.");
		}
		return ResponseEntity.status(HttpStatus.OK).body("정상적으로 처리되었습니다.");
	}
}
