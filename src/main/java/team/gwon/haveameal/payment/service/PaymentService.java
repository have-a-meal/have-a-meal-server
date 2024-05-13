package team.gwon.haveameal.payment.service;

import java.io.IOException;

import com.siot.IamportRestClient.exception.IamportResponseException;

import team.gwon.haveameal.payment.dto.GetTiketPriceDto;

public interface PaymentService {

	void init();

	void test();

	void getTiketPrice(GetTiketPriceDto getTiketPriceDto);

	void verifyPayment(String impUid) throws IamportResponseException, IOException;

	void createPayment();

}
