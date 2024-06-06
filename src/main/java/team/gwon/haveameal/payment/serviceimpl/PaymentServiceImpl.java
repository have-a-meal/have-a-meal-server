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
import team.gwon.haveameal.common.util.UuidProvider;
import team.gwon.haveameal.payment.dto.PaymentTransactionResponseDto;
import team.gwon.haveameal.payment.dto.PaymentVerifyRequestDto;
import team.gwon.haveameal.payment.dto.TicketBuyRequestDto;
import team.gwon.haveameal.payment.dto.TicketBuyResponseDto;
import team.gwon.haveameal.payment.dto.TicketPriceRequestDto;
import team.gwon.haveameal.payment.dto.TicketPriceResponseDto;
import team.gwon.haveameal.payment.entity.Course;
import team.gwon.haveameal.payment.entity.CourseWithDetail;
import team.gwon.haveameal.payment.entity.Payment;
import team.gwon.haveameal.payment.entity.PaymentDetail;
import team.gwon.haveameal.payment.entity.PaymentWithCourseIncludeDetail;
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
		CourseWithDetail courseWithDetail = paymentMapper.getTicketPrice(ticketPriceRequestDto.toCourseWithDetail());
		TicketPriceResponseDto ticketPriceResponseDto = TicketPriceResponseDto.fromTicketPrice(courseWithDetail);
		log.info("getTicketPrice 결과 : {}", courseWithDetail);
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
		Long impAmount = iamportResponse.getResponse().getAmount().longValue(); // 결제 금액
		String name = iamportResponse.getResponse().getName(); // 상품명
		String status = iamportResponse.getResponse().getStatus(); //결제 상태
		Date time = iamportResponse.getResponse().getPaidAt();
		log.info("데이터 확인 : , amountIamport = {}, name = {}, status = {}, time = {}", impAmount, name, status, time);

		PaymentDetail impPaymentDetail = paymentVerifyRequestDto.toPaymentDetail(iamportResponse);

		Payment payment = paymentMapper.getPaymentByPaymentDetail(impPaymentDetail);
		CourseWithDetail courseWithDetail = paymentMapper.getTicketPrice(CourseWithDetail.builder()
			.course(
				Course.builder().courseId(payment.getCourseId()).build())
			.build()); // Entity to Entity라서 얘는 어디에 빼서 만들어 두면 좋을지 고민임...
		// 아임포트에 남은 PaidAt과 우리 서버에 저장된 Payment requestAt을 비교해서 더 최신 결제 내용인지 확인하는 과정 필요.
		// 아임포트에 결제된 가격과 payment에 저장된 courseId에 해당하는 가격이 같은지 비교하는 과정 필요.
		// if(iamportResponse.getResponse().getPaidAt() > paymentMapper.getPayment())

		// 결제 상세 정보 삽입.
		if (courseWithDetail.getCourseDetail().getPrice() == impAmount) {
			paymentMapper.createPaymentDetail(impPaymentDetail);
		}
		// 가격이 틀리면 실패.
	}

	@Override
	public TicketBuyResponseDto createPayment(TicketBuyRequestDto ticketBuyRequestDto) {
		Payment payment = ticketBuyRequestDto.toPayment();
		log.info("Payment : {}", payment);
		int paymentResult = paymentMapper.createPayment(payment);
		// 실패 시 분기 처리
		return TicketBuyResponseDto.builder()
			.paymentId(UuidProvider.byteToString(payment.getPaymentId())).build();
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
