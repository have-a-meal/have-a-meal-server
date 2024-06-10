package team.gwon.haveameal.payment.service;

import java.io.IOException;
import java.util.List;

import com.siot.IamportRestClient.exception.IamportResponseException;

import team.gwon.haveameal.payment.dto.PaymentTransactionResponseDto;
import team.gwon.haveameal.payment.dto.PaymentVerifyRequestDto;
import team.gwon.haveameal.payment.dto.TicketBuyRequestDto;
import team.gwon.haveameal.payment.dto.TicketBuyResponseDto;
import team.gwon.haveameal.payment.dto.TicketPriceRequestDto;
import team.gwon.haveameal.payment.dto.TicketPriceResponseDto;

public interface PaymentService {

	void init();

	void test();

	TicketPriceResponseDto getTicketPrice(TicketPriceRequestDto getTiketPriceDto);

	void verifyPayment(PaymentVerifyRequestDto paymentVerifyRequestDto) throws IamportResponseException, IOException;

	TicketBuyResponseDto createPayment(TicketBuyRequestDto ticketBuyRequestDto);

	List<PaymentTransactionResponseDto> getPaymentTransaction(String memberId);
}
