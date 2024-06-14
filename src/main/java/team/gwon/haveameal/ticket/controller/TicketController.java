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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.zxing.WriterException;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import team.gwon.haveameal.common.component.swagger.SwaggerApiBadRequest;
import team.gwon.haveameal.common.component.swagger.SwaggerApiSuccess;
import team.gwon.haveameal.ticket.domain.QrCodeRefreshDto;
import team.gwon.haveameal.ticket.domain.QrCodeRequestDto;
import team.gwon.haveameal.ticket.domain.QrCodeResponseDto;
import team.gwon.haveameal.ticket.domain.QrCodeUseRequestDto;
import team.gwon.haveameal.ticket.domain.QrCodeUseResponseDto;
import team.gwon.haveameal.ticket.domain.TicketFindRequestDto;
import team.gwon.haveameal.ticket.domain.TicketFindResponseDto;
import team.gwon.haveameal.ticket.domain.TicketQuantityRequestDto;
import team.gwon.haveameal.ticket.domain.TicketQuantityResponseDto;
import team.gwon.haveameal.ticket.service.TicketService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ticket")
public class TicketController {

	private final TicketService ticketService;

	@SwaggerApiSuccess(summary = "식권 조회", implementation = TicketFindResponseDto.class)
	@SwaggerApiBadRequest
	@GetMapping("/{memberId}")
	public ResponseEntity<List<TicketFindResponseDto>> findAllTickets(@PathVariable("memberId") String memberId) {
		List<TicketFindResponseDto> tickets = ticketService.findAllTickets(TicketFindRequestDto.from(memberId));

		return ResponseEntity.status(HttpStatus.OK).body(tickets);
	}

	@SwaggerApiSuccess(summary = "보유 식권 개수 조회", implementation = TicketQuantityResponseDto.class)
	@SwaggerApiBadRequest
	@PostMapping("/quantity")
	public ResponseEntity<TicketQuantityResponseDto> findMyTicketQuantity(
		@RequestBody TicketQuantityRequestDto ticketQuantityRequestDto) {
		TicketQuantityResponseDto response = ticketService.getMyTicketQuantity(ticketQuantityRequestDto);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@SwaggerApiSuccess(summary = "식권 QR 생성", implementation = QrCodeResponseDto.class)
	@SwaggerApiBadRequest
	@PostMapping("")
	public ResponseEntity<QrCodeResponseDto> getQrCode(@RequestBody QrCodeRequestDto qrCodeRequestDto) throws
		IOException,
		WriterException {
		QrCodeResponseDto qrCode = ticketService.getQrCode(qrCodeRequestDto);

		return ResponseEntity.status(HttpStatus.OK).body(qrCode);
	}

	@SwaggerApiSuccess(summary = "식권 QR 확인", implementation = QrCodeUseResponseDto.class)
	@SwaggerApiBadRequest
	@PutMapping("")
	public ResponseEntity<QrCodeUseResponseDto> useQrCode(
		@RequestBody @Valid QrCodeUseRequestDto qrCodeUseRequestDto) throws
		JsonProcessingException {
		QrCodeUseResponseDto response = ticketService.useQrCode(qrCodeUseRequestDto);

		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@SwaggerApiSuccess(summary = "식권 QR 새로고침", implementation = QrCodeResponseDto.class)
	@SwaggerApiBadRequest
	@PostMapping("/refresh")
	public ResponseEntity<QrCodeResponseDto> refreshQrCode(@RequestBody @Valid QrCodeRefreshDto qrCodeRefreshDto) throws
		IOException,
		WriterException {
		QrCodeResponseDto qrCode = ticketService.getQrCode(qrCodeRefreshDto.getQrCodeRequestDto());

		return ResponseEntity.status(HttpStatus.OK).body(qrCode);
	}

}
