package team.gwon.haveameal.ticket.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.zxing.WriterException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import team.gwon.haveameal.common.component.TokenProvider;
import team.gwon.haveameal.common.domain.Token;
import team.gwon.haveameal.member.util.RedisUtil;
import team.gwon.haveameal.payment.entity.PaymentWithCourseIncludeDetail;
import team.gwon.haveameal.ticket.component.QrGenerator;
import team.gwon.haveameal.ticket.domain.QrCodeRequestDto;
import team.gwon.haveameal.ticket.domain.QrCodeResponseDto;
import team.gwon.haveameal.ticket.domain.QrCodeUseRequestDto;
import team.gwon.haveameal.ticket.domain.QrCodeUseResponseDto;
import team.gwon.haveameal.ticket.domain.QrContent;
import team.gwon.haveameal.ticket.domain.TicketEntity;
import team.gwon.haveameal.ticket.domain.TicketFindRequestDto;
import team.gwon.haveameal.ticket.domain.TicketFindResponseDto;
import team.gwon.haveameal.ticket.domain.TicketQuantityRequestDto;
import team.gwon.haveameal.ticket.domain.TicketQuantityResponseDto;
import team.gwon.haveameal.ticket.exception.TicketErrorCode;
import team.gwon.haveameal.ticket.exception.TicketException;
import team.gwon.haveameal.ticket.mapper.TicketMapper;

@Slf4j
@RequiredArgsConstructor
@Service
public class TicketService {
	private final TicketMapper ticketMapper;
	private final QrGenerator qrGenerator;
	private final TokenProvider tokenProvider;
	private final RedisUtil redisUtil;
	private final ObjectMapper objectMapper;

	public List<TicketFindResponseDto> findAllTickets(TicketFindRequestDto ticketFindRequestDto) {

		List<PaymentWithCourseIncludeDetail> tickets = ticketMapper.findAllMyTickets(
			ticketFindRequestDto.toMemberEntity());

		if (tickets.isEmpty()) {
			throw new TicketException(TicketErrorCode.TICKET_NOT_FOUND);
		}

		log.info("tickets : {}", tickets);
		List<TicketFindResponseDto> response = new ArrayList<>();
		for (PaymentWithCourseIncludeDetail paymentWithCourseIncludeDetail : tickets) {
			response.add(TicketFindResponseDto.from(paymentWithCourseIncludeDetail));
		}
		return response;
	}

	@Transactional
	public QrCodeResponseDto getQrCode(QrCodeRequestDto qrCodeRequestDto) throws IOException, WriterException {
		Long ticketId = ticketMapper.getQrCode(qrCodeRequestDto.toPaymentWithCourseIncludeDetail());
		Token token = tokenProvider.createToken(
			QrContent.builder().ticketId(ticketId).memberId(qrCodeRequestDto.getMemberId()).build());
		/* 사용자 인증으로 사용하는 토큰과 key 값이 겹칠 수 있음
			추후 개발시 qr 용 key 값과 인증용 key 값 분리 필요.
		 */
		redisUtil.setData(qrCodeRequestDto.getMemberId(), objectMapper.writeValueAsString(token));
		byte[] qrCode = qrGenerator.generateQrImage(token, qrCodeRequestDto.getWidth(), qrCodeRequestDto.getHeight());
		return QrCodeResponseDto.from(qrCode);
	}

	@Transactional
	public QrCodeUseResponseDto useQrCode(QrCodeUseRequestDto qrCodeUseRequestDto) throws JsonProcessingException {
		String accessToken = qrCodeUseRequestDto.getAccessToken();
		QrContent content = objectMapper.readValue(
			tokenProvider.getClaims(accessToken).getSubject(), QrContent.class);
		log.info("content : {}", content);
		Token savedToken = objectMapper.readValue(redisUtil.getData(content.getMemberId()), Token.class);

		log.info("savedToken : {}", savedToken);
		if (accessToken.equals(savedToken.getAccessToken())) {

			Integer result = ticketMapper.useQrCode(
				TicketEntity.builder().ticketId(content.getTicketId()).mealId(qrCodeUseRequestDto.getMealId()).build());

			if (result == 1) {
				return QrCodeUseResponseDto.from("성공!");
			}
		}
		throw new RuntimeException();
	}

	public TicketQuantityResponseDto getMyTicketQuantity(TicketQuantityRequestDto ticketQuantityRequestDto) {
		int quantity = ticketMapper.getMyTicketQuantity(ticketQuantityRequestDto);
		return TicketQuantityResponseDto.from(quantity);
	}
}
