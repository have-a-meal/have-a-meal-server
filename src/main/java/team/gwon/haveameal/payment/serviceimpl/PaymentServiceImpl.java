package team.gwon.haveameal.payment.serviceimpl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import team.gwon.haveameal.payment.service.PaymentService;

@Slf4j
@Service
public class PaymentServiceImpl implements PaymentService {

	@Value("${portone.restApiKey:null}")
	private String restApiKey;

	@Value("${portone.restApiSecret:null}")
	private String restApiSecret;

	@Value("${portone.noValue:null}")
	private String noValue;

	@Override
	public void test() {
		log.info("restApiKey : " + restApiKey);
		log.info("restApiSecret : " + restApiSecret);
		log.info("noValue : " + noValue.equals("null"));
		log.info("noValue : " + (noValue == null ? "null" : "not null"));
	}
}
