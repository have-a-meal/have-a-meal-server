package team.gwon.haveameal.common.logging;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestController
@RequestMapping("test")
public class LogFilterTestController {
	@GetMapping("/logTest")
	public ResponseEntity<?> getLogTest() {
		return ResponseEntity.status(HttpStatus.OK).body("정상적으로 처리되었습니다.");
	}
}
