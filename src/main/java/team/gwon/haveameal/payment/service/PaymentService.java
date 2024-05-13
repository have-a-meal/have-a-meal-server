package team.gwon.haveameal.payment.service;

import java.io.IOException;

import com.siot.IamportRestClient.exception.IamportResponseException;

public interface PaymentService {

	void init();

	void test();

	void verifyPayment(String impUid) throws IamportResponseException, IOException;
}
