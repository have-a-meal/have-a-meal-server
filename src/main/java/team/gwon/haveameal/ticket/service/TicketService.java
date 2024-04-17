package team.gwon.haveameal.ticket.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import team.gwon.haveameal.payment.entity.PaymentWithCourseIncludeDetail;
import team.gwon.haveameal.ticket.domain.TicketFindRequestDto;
import team.gwon.haveameal.ticket.domain.TicketFindResponseDto;
import team.gwon.haveameal.ticket.mapper.TicketMapper;

@RequiredArgsConstructor
@Service
public class TicketService {
	private final TicketMapper ticketMapper;

	public List<TicketFindResponseDto> findAllTickets(TicketFindRequestDto ticketFindRequestDto) {
		List<PaymentWithCourseIncludeDetail> tickets = ticketMapper.findAllMyTickets(
			TicketFindRequestDto.from(ticketFindRequestDto)).orElseThrow(RuntimeException::new);
		// CustomException 추가 예정.

		List<TicketFindResponseDto> response = new ArrayList<>();
		for (PaymentWithCourseIncludeDetail paymentWithCourseIncludeDetail : tickets) {
			response.add(TicketFindResponseDto.of(paymentWithCourseIncludeDetail));
		}
		return response;
	}
}
