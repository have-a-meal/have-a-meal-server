package team.gwon.haveameal.ticket.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.zxing.WriterException;

import lombok.RequiredArgsConstructor;
import team.gwon.haveameal.ticket.domain.QrCodeRequestDto;
import team.gwon.haveameal.ticket.domain.QrCodeResponseDto;
import team.gwon.haveameal.ticket.domain.QrCodeUseRequestDto;
import team.gwon.haveameal.ticket.domain.QrCodeUseResponseDto;
import team.gwon.haveameal.ticket.domain.TicketFindRequestDto;
import team.gwon.haveameal.ticket.domain.TicketFindResponseDto;
import team.gwon.haveameal.ticket.service.TicketService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ticket")
public class TicketController {

	private final TicketService ticketService;

	@GetMapping("/{memberId}")
	public ResponseEntity<List<TicketFindResponseDto>> findAllTickets(@PathVariable("memberId") String memberId) {
		List<TicketFindResponseDto> tickets = ticketService.findAllTickets(TicketFindRequestDto.from(memberId));

		return ResponseEntity.status(HttpStatus.OK).body(tickets);
	}

	@PostMapping("")
	public ResponseEntity<QrCodeResponseDto> getQrCode(@RequestBody QrCodeRequestDto qrCodeRequestDto) throws
		IOException,
		WriterException {
		QrCodeResponseDto qrCode = ticketService.getQrCode(qrCodeRequestDto);

		return ResponseEntity.status(HttpStatus.OK).body(qrCode);
	}

	@PutMapping("")
	public ResponseEntity<QrCodeUseResponseDto> useQrCode(
		@RequestBody QrCodeUseRequestDto qrCodeUseRequestDto) {
		QrCodeUseResponseDto response = ticketService.useQrCode(qrCodeUseRequestDto);

		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

}
