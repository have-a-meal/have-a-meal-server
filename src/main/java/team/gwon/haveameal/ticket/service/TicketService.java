package team.gwon.haveameal.ticket.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.zxing.WriterException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import team.gwon.haveameal.payment.entity.PaymentWithCourseIncludeDetail;
import team.gwon.haveameal.ticket.component.QrGenerator;
import team.gwon.haveameal.ticket.domain.QrCodeRequestDto;
import team.gwon.haveameal.ticket.domain.QrCodeResponseDto;
import team.gwon.haveameal.ticket.domain.TicketFindRequestDto;
import team.gwon.haveameal.ticket.domain.TicketFindResponseDto;
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
		// CustomException 추가 예정.
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
}
