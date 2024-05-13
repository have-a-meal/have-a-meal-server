package team.gwon.haveameal.payment.controller;

import java.io.IOException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.siot.IamportRestClient.exception.IamportResponseException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import team.gwon.haveameal.payment.service.PaymentService;

@Slf4j
@RequestMapping("/payment")
@RestController
@RequiredArgsConstructor
public class PaymentController {

	private final PaymentService paymentService;

	@GetMapping("/verify/{impUid}")
	public void paymentByImpUid(@PathVariable("impUid") String impUid) throws IamportResponseException, IOException {
	public void verifyPayment(@PathVariable("impUid") String impUid) throws IamportResponseException, IOException {
		paymentService.verifyPayment(impUid);
	}

	@GetMapping("/test")
	public void test() {
		paymentService.test();
	}
}
