package team.gwon.haveameal.unit.payment;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;
import team.gwon.haveameal.payment.dto.TicketPriceRequestDto;
import team.gwon.haveameal.payment.dto.TicketPriceResponseDto;
import team.gwon.haveameal.payment.service.PaymentService;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Slf4j
@SpringBootTest
class PaymentServiceTest {

	@Autowired
	private PaymentService paymentService;

	@DisplayName("결제요청")
	@Test
	void requestPayment() {
		final PaymentRequest request = new PaymentRequest();

	}

	@DisplayName("식권가격 조회")
	@Test
	void selectTicketPrice() {
		TicketPriceRequestDto getTicketPriceDto = new TicketPriceRequestDto("중식", "B", "21860004");
		TicketPriceResponseDto ticketPriceResponseDto = paymentService.getTicketPrice(getTicketPriceDto);
		log.info("식권 가격 조회 결과 : {}", ticketPriceResponseDto);
	}

	private class PaymentRequest {

	}

	@Test
	public void 시간테스트(){
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime now2 = LocalDateTime.ofInstant(
				Instant.ofEpochMilli(System.currentTimeMillis()),
				ZoneId.of("Asia/Seoul"));
		log.info("LocalDateTime : {}", now);
		log.info("time test : {}", now2);
	}
}
