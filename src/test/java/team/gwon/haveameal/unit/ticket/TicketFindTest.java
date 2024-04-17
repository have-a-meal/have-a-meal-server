package team.gwon.haveameal.unit.ticket;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;
import team.gwon.haveameal.ticket.domain.TicketFindRequestDto;
import team.gwon.haveameal.ticket.domain.TicketFindResponseDto;
import team.gwon.haveameal.ticket.service.TicketService;

@Slf4j
@SpringBootTest
class TicketFindTest {

	@Autowired
	private TicketService ticketService;

	@DisplayName("티켓 조회 테스트")
	@Test
	void findTicket() {
		// given
		String memberId = "21860004";

		List<TicketFindResponseDto> list = ticketService.findAllTickets(TicketFindRequestDto.from(memberId));
		log.info("list : {}", list);
	}

}
