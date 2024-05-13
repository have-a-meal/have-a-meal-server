package team.gwon.haveameal.payment.serviceimpl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import team.gwon.haveameal.payment.service.PaymentService;

@Slf4j
@RequiredArgsConstructor
@PropertySource("classpath:/config/application-portone.properties")
@Service
public class PaymentServiceImpl implements PaymentService {

	@Value("${portone.restApiKey:null}")
	private String restApiKey;

	@Value("${portone.restApiSecret:null}")
	private String restApiSecret;

	@Value("${portone.noValue:null}")
	private String noValue;

	private IamportClient iamportClient;

	@PostConstruct
	public void init() {
		// @Value 필드가 주입되기 전 동작 방지
		// @PostConstruct 사용해서 IamportClient 초기화
		this.iamportClient = new IamportClient(restApiKey, restApiSecret);
	}

	@Override
	public void test() {
		log.info("restApiKey : " + restApiKey);
		log.info("restApiSecret : " + restApiSecret);
		log.info("noValue : " + noValue.equals("null"));
		log.info("noValue : " + (noValue == null ? "null" : "not null"));
	}

	@Override
	public void verifyPayment(String impUid) throws IamportResponseException, IOException {
		log.info("portOne api 연동 확인 : " + iamportClient);
		IamportResponse<Payment> iamportResponse = iamportClient.paymentByImpUid(impUid);
		int amount = paymentMapper.amount(impUid);
		Long amountIamport = iamportResponse.getResponse().getAmount().longValue(); // 결제 금액
		String name = iamportResponse.getResponse().getName(); // 상품명
		String status = iamportResponse.getResponse().getStatus(); //결제 상태
		log.info("데이터 확인 : , amount = {}, name = {}, status = {}", amount, name, status);
	}
}
