package team.gwon.haveameal.payment.serviceimpl;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import team.gwon.haveameal.payment.dto.PaymentTransactionResponseDto;
import team.gwon.haveameal.payment.dto.PaymentVerifyRequestDto;
import team.gwon.haveameal.payment.dto.TicketBuyRequestDto;
import team.gwon.haveameal.payment.dto.TicketBuyResponseDto;
import team.gwon.haveameal.payment.dto.TicketPriceRequestDto;
import team.gwon.haveameal.payment.dto.TicketPriceResponseDto;
import team.gwon.haveameal.payment.entity.Payment;
import team.gwon.haveameal.payment.entity.PaymentDetail;
import team.gwon.haveameal.payment.entity.PaymentWithCourseIncludeDetail;
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
	public TicketPriceResponseDto getTicketPrice(TicketPriceRequestDto ticketPriceRequestDto) {
		TicketPrice ticketPrice = paymentMapper.getTicketPrice(ticketPriceRequestDto.toCourseWithDetail());
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
	public void verifyPayment(PaymentVerifyRequestDto paymentVerifyRequestDto) throws
		IamportResponseException,
		IOException {
		log.info("portOne api 연동 확인 : " + iamportClient);
		IamportResponse<com.siot.IamportRestClient.response.Payment> iamportResponse = iamportClient.paymentByImpUid(
			paymentVerifyRequestDto.getImpUid());
		int amount = paymentMapper.amount(paymentVerifyRequestDto.getImpUid());
		Long amountIamport = iamportResponse.getResponse().getAmount().longValue(); // 결제 금액
		String name = iamportResponse.getResponse().getName(); // 상품명
		String status = iamportResponse.getResponse().getStatus(); //결제 상태
		Date time = iamportResponse.getResponse().getPaidAt();
		log.info("데이터 확인 : , amount = {}, name = {}, status = {}, time = {}", amount, name, status, time);

		PaymentDetail paymentDetail = paymentVerifyRequestDto.toPaymentDetail(iamportResponse);

		Payment payment = paymentMapper.getPaymentByPaymentDetail(paymentDetail);

		// 아임포트에 남은 PaidAt과 우리 서버에 저장된 Payment requestAt을 비교해서 더 최신 결제 내용인지 확인하는 과정 필요.
		// if(iamportResponse.getResponse().getPaidAt() > paymentMapper.getPayment())

		// 결제 상세 정보 삽입.
		paymentMapper.createPaymentDetail(paymentDetail);
	}

	@Override
	public TicketBuyResponseDto createPayment(TicketBuyRequestDto ticketBuyRequestDto) {
		Payment payment = ticketBuyRequestDto.toPayment();
		log.info("Payment : {}", payment);
		int paymentResult = paymentMapper.createPayment(payment);
		// 실패 시 분기 처리
		return TicketBuyResponseDto.builder()
			.paymentId(payment.getPaymentId()).build();
	}

	@Transactional
	@Override
	public List<PaymentTransactionResponseDto> getPaymentTransaction(String memberId) {
		log.info("memberId : {}", memberId);

		List<PaymentWithCourseIncludeDetail> paymentWithCourseIncludeDetailList = paymentMapper.getPaymentTransaction(
			memberId);
		return paymentWithCourseIncludeDetailList.stream()
			.map(PaymentTransactionResponseDto::fromPaymentWithCourseIncludeDetail)
			.collect(Collectors.toList());
	}
}
