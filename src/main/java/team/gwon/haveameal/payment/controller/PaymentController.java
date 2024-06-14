package team.gwon.haveameal.payment.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<?> verifyPayment(@Valid PaymentVerifyRequestDto paymentVerifyRequestDto) throws
		IamportResponseException,
		IOException {
		log.info("paymentVerifyRequestDto : {}", paymentVerifyRequestDto);
		boolean flag = paymentService.verifyPayment(paymentVerifyRequestDto);
		if (flag) {
			return ResponseEntity.status(HttpStatus.OK).body("결제 검증이 완료되었습니다.");
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("결제 정보가 올바르지 않습니다.");
	}

	@GetMapping("/tiketPrice")
	public ResponseEntity<TicketPriceResponseDto> getTiketPrice(@Valid TicketPriceRequestDto ticketPriceRequestDto) {
		log.info("ticketPriceRequestDto DTO : {}", ticketPriceRequestDto);
		TicketPriceResponseDto ticketPriceResponseDto = paymentService.getTicketPrice(ticketPriceRequestDto);
		return ResponseEntity.status(HttpStatus.OK).body(ticketPriceResponseDto);
	}

	@PostMapping("")
	public ResponseEntity<TicketBuyResponseDto> buyTiket(@RequestBody @Valid TicketBuyRequestDto ticketBuyRequestDto) {
		log.info("ticketBuyRequestDto : {}", ticketBuyRequestDto);

		TicketBuyResponseDto ticketBuyResponseDto = paymentService.createPayment(ticketBuyRequestDto);
		log.info("TicketBuyResponseDto : {}", ticketBuyResponseDto);

		return ResponseEntity.status(HttpStatus.CREATED).body(ticketBuyResponseDto);
	}

	@GetMapping("/transaction/{memberId}")
	public ResponseEntity<List<PaymentTransactionResponseDto>> getPaymentTransactionList(@PathVariable String memberId) {
		List<PaymentTransactionResponseDto> paymentTransactionResponseDtoList = paymentService.getPaymentTransaction(
			memberId);
		log.info("결제 목록 : {}", paymentTransactionResponseDtoList.toString());

		return ResponseEntity.status(HttpStatus.OK).body(paymentTransactionResponseDtoList);
	}
}
