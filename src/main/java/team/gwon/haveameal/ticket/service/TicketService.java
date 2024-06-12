package team.gwon.haveameal.ticket.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.zxing.WriterException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import team.gwon.haveameal.common.domain.Token;
import team.gwon.haveameal.common.util.TokenProvider;
import team.gwon.haveameal.payment.entity.PaymentWithCourseIncludeDetail;
import team.gwon.haveameal.ticket.component.QrGenerator;
import team.gwon.haveameal.ticket.domain.QrCodeRequestDto;
import team.gwon.haveameal.ticket.domain.QrCodeResponseDto;
import team.gwon.haveameal.ticket.domain.QrCodeUseResponseDto;
import team.gwon.haveameal.ticket.domain.TicketFindRequestDto;
import team.gwon.haveameal.ticket.domain.TicketFindResponseDto;
import team.gwon.haveameal.ticket.exception.TicketErrorCode;
import team.gwon.haveameal.ticket.exception.TicketException;
import team.gwon.haveameal.ticket.mapper.TicketMapper;

@Slf4j
@RequiredArgsConstructor
@Service
public class TicketService {
	private final TicketMapper ticketMapper;
	private final QrGenerator qrGenerator;

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

	public QrCodeResponseDto getQrCode(QrCodeRequestDto qrCodeRequestDto) throws IOException, WriterException {
		Integer ticketId = ticketMapper.getQrCode(qrCodeRequestDto.toPaymentWithCourseIncludeDetail());
		byte[] qrCode = qrGenerator.generateQrImage(ticketId, qrCodeRequestDto.getWidth(),
			qrCodeRequestDto.getHeight());
		return QrCodeResponseDto.from(qrCode);
	}

	@Transactional
	public QrCodeUseResponseDto useQrCode(Token token) {
		if (TokenProvider.vaild()) {
			Integer result = ticketMapper.useQrCode(TokenProvider.toTicketEntity(token));
			if (result == 1) {
				return QrCodeUseResponseDto.from("성공!");
			}
		}
		return QrCodeUseResponseDto.from("실패!");
	}
}
