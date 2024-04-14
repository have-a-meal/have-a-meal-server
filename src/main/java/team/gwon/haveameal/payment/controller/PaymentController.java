package team.gwon.haveameal.payment.controller;

import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import team.gwon.haveameal.payment.service.PaymentService;

@Slf4j
@RequestMapping("/payment")
@RestController
@RequiredArgsConstructor
@PropertySource("classpath:/config/application-portone.properties")
public class PaymentController {

	private final PaymentService paymentService;

	@GetMapping("/verify/{imp_uid}")
	public void paymentByImpUid() {

	}

	@GetMapping("/test")
	public void test() {
		paymentService.test();
	}
}
