package team.gwon.haveameal.unit.ticket;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.google.zxing.WriterException;

import lombok.extern.slf4j.Slf4j;
import team.gwon.haveameal.ticket.domain.QrCodeRequestDto;
import team.gwon.haveameal.ticket.domain.QrCodeResponseDto;
import team.gwon.haveameal.ticket.domain.QrCodeUseRequestDto;
import team.gwon.haveameal.ticket.domain.QrCodeUseResponseDto;
import team.gwon.haveameal.ticket.domain.TicketFindRequestDto;
import team.gwon.haveameal.ticket.domain.TicketFindResponseDto;
import team.gwon.haveameal.ticket.service.TicketService;

@Slf4j
@SpringBootTest
public class TicketTest {

	@Autowired
	TicketService ticketService;

	@DisplayName("티켓 조회 테스트")
	@Test
	void findTicket() {
		// given
		String memberId = "21860004";

		List<TicketFindResponseDto> list = ticketService.findAllTickets(TicketFindRequestDto.from(memberId));
		log.info("list : {}", list);
	}

	@DisplayName("QrCode 이미지 로드 테스트")
	@Test
	void getQrCode() throws IOException, WriterException {
		// given
		String memberId = "21860004";
		String courseType = "A";
		String timing = "조식";
		int width = 300;
		int height = 300;
		QrCodeRequestDto qrCodeRequestDto = QrCodeRequestDto.of(memberId, courseType, timing, width, height);

		QrCodeResponseDto qrCode = ticketService.getQrCode(qrCodeRequestDto);
		log.info("response: {}", qrCode);

		ByteArrayInputStream inputStream = new ByteArrayInputStream(qrCode.getQrCode());
		BufferedImage bufferedImage = ImageIO.read(inputStream);

		// ImageIO.write(bufferedImage, "png", new File("./image.png"));
	}

	@DisplayName("QrCode 사용 시, 사용 변경 테스트")
	@Test
	void confirmQrCode() {
		// given
		Long ticketId = 1L;
		Long mealId = 1L;

		QrCodeUseRequestDto request = QrCodeUseRequestDto.of(ticketId, mealId);

		QrCodeUseResponseDto response = ticketService.useQrCode(request);

		assertEquals(response.getMessage(), "성공!");

	}
}
