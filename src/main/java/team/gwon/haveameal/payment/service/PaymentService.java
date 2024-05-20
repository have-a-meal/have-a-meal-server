package team.gwon.haveameal.payment.service;

import java.io.IOException;

import com.siot.IamportRestClient.exception.IamportResponseException;

import team.gwon.haveameal.payment.dto.TicketPriceRequestDto;
import team.gwon.haveameal.payment.dto.TicketPriceResponseDto;

public interface PaymentService {

	void init();

	void test();

	TicketPriceResponseDto getTicketPrice(TicketPriceRequestDto getTiketPriceDto);

	void verifyPayment(String impUid) throws IamportResponseException, IOException;

	void createPayment();

}
