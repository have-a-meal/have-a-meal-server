package team.gwon.haveameal.payment.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/payment")
@RestController
@RequiredArgsConstructor
@PropertySource("classpath:/config/application-portone.properties")
public class PaymentController {

	@Value("${portone.restApiKey:null}")
	private String restApiKey;

	@Value("${portone.restApiSecret:null}")
	private String restApiSecret;

	@Value("${portone.noValue:null}")
	private String noValue;

	// private final PaymentService paymentService;

	@GetMapping("/verify/{imp_uid}")
	public void paymentByImpUid() {

	}

	@GetMapping("/test")
	public void test() {
		log.info("restApiKey : " + restApiKey);
		log.info("restApiSecret : " + restApiSecret);
		log.info("noValue : " + noValue.equals("null"));
		log.info("noValue : " + (noValue == null ? "null" : "not null"));
	}
}
