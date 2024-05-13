package team.gwon.haveameal.payment.controller;

import java.io.IOException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.siot.IamportRestClient.exception.IamportResponseException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import team.gwon.haveameal.payment.dto.GetTiketPriceDto;
import team.gwon.haveameal.payment.service.PaymentService;

@Slf4j
@RequestMapping("/payment")
@RestController
@RequiredArgsConstructor
public class PaymentController {

	private final PaymentService paymentService;

	@GetMapping("/verify/{impUid}")
	public void verifyPayment(@PathVariable("impUid") String impUid) throws IamportResponseException, IOException {
		paymentService.verifyPayment(impUid);
	}

	@GetMapping("/getTiketPrice")
	public void getTiketPrice(@PathVariable("timing") String timing, @PathVariable("courseType") String courseType,
		@PathVariable("memberType") String memberType) {
		GetTiketPriceDto getTiketPriceDto = new GetTiketPriceDto(timing, courseType, memberType);
		paymentService.getTiketPrice(getTiketPriceDto);
	}

	@PostMapping("/buy")
	public void buyTiket() {
		paymentService.createPayment();
	}

	@GetMapping("/test")
	public void test() {
		paymentService.test();
	}
}
