package team.gwon.haveameal.payment.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.siot.IamportRestClient.exception.IamportResponseException;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import team.gwon.haveameal.payment.dto.PaymentTransactionResponseDto;
import team.gwon.haveameal.payment.dto.PaymentVerifyRequestDto;
import team.gwon.haveameal.payment.dto.TicketBuyRequestDto;
import team.gwon.haveameal.payment.dto.TicketBuyResponseDto;
import team.gwon.haveameal.payment.dto.TicketPriceRequestDto;
import team.gwon.haveameal.payment.dto.TicketPriceResponseDto;
import team.gwon.haveameal.payment.service.PaymentService;

@Slf4j
@RequestMapping("/payment")
@RestController
@RequiredArgsConstructor
public class PaymentController {

	private final PaymentService paymentService;

	@GetMapping("/verify")
	public void verifyPayment(@Valid PaymentVerifyRequestDto paymentVerifyRequestDto) throws
		IamportResponseException,
		IOException {
		log.info("paymentVerifyRequestDto : {}", paymentVerifyRequestDto);
		paymentService.verifyPayment(paymentVerifyRequestDto);
	}

	@GetMapping("/tiketPrice")
	public void getTiketPrice(@Valid TicketPriceRequestDto ticketPriceRequestDto) {
		log.info("ticketPriceRequestDto DTO : {}", ticketPriceRequestDto);
		TicketPriceResponseDto ticketPriceResponseDto = paymentService.getTicketPrice(ticketPriceRequestDto);
	}

	@PostMapping("")
	public void buyTiket(@RequestBody @Valid TicketBuyRequestDto ticketBuyRequestDto) {
		log.info("ticketBuyRequestDto : {}", ticketBuyRequestDto);

		TicketBuyResponseDto ticketBuyResponseDto = paymentService.createPayment(ticketBuyRequestDto);
		log.info("TicketBuyResponseDto : {}", ticketBuyResponseDto);
	}

	@GetMapping("/test")
	public void test() {
		paymentService.test();
	}

	@GetMapping("/transaction/{memberId}")
	public void getPaymentTransactionList(@PathVariable String memberId) {
		List<PaymentTransactionResponseDto> paymentTransactionResponseDtoList = paymentService.getPaymentTransaction(
			memberId);
		log.info("결제 목록 : {}", paymentTransactionResponseDtoList.toString());
	}
}
