package team.gwon.haveameal.payment.serviceimpl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import team.gwon.haveameal.payment.dto.TicketBuyRequestDto;
import team.gwon.haveameal.payment.dto.TicketBuyResponseDto;
import team.gwon.haveameal.payment.dto.TicketPriceRequestDto;
import team.gwon.haveameal.payment.dto.TicketPriceResponseDto;
import team.gwon.haveameal.payment.entity.Payment;
import team.gwon.haveameal.payment.entity.TicketPrice;
import team.gwon.haveameal.payment.mapper.PaymentMapper;
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

	private IamportClient iamportClient;

	private final PaymentMapper paymentMapper;

	@PostConstruct
	public void init() {
		// @Value 필드가 주입되기 전 동작 방지
		// @PostConstruct 사용해서 IamportClient 초기화
		this.iamportClient = new IamportClient(restApiKey, restApiSecret);
	}

	@Override
	public TicketPriceResponseDto getTicketPrice(TicketPriceRequestDto getTicketPriceDto) {
		TicketPrice ticketPrice = paymentMapper.getTicketPrice(getTicketPriceDto.toCourseWithDetail());
		TicketPriceResponseDto ticketPriceResponseDto = TicketPriceResponseDto.fromTicketPrice(ticketPrice);
		log.info("getTicketPrice 결과 : {}", ticketPrice);
		return ticketPriceResponseDto;
	}

	@Override
	public void test() {
		log.info("restApiKey : " + restApiKey);
		log.info("restApiSecret : " + restApiSecret);
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

	@Override
	public void createPayment() {
		int result = paymentMapper.createPayment();
	public TicketBuyResponseDto createPayment(TicketBuyRequestDto ticketBuyRequestDto) {
		Payment payment = ticketBuyRequestDto.toPayment();
		log.info("Payment : {}", payment);
		int paymentResult = paymentMapper.createPayment(payment);
		// 실패 시 분기 처리
		return TicketBuyResponseDto.builder()
			.paymentId(payment.getPaymentId()).build();
	}
}
