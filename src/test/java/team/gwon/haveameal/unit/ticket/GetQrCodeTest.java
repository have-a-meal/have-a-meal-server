package team.gwon.haveameal.unit.ticket;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.google.zxing.WriterException;

import lombok.extern.slf4j.Slf4j;
import team.gwon.haveameal.ticket.domain.QrCodeRequestDto;
import team.gwon.haveameal.ticket.domain.QrCodeResponseDto;
import team.gwon.haveameal.ticket.service.TicketService;

@Slf4j
@SpringBootTest
class GetQrCodeTest {

	@Autowired
	private TicketService ticketService;

	@DisplayName("QrCode 이미지 로드 테스트")
	@Test
	void findTicket() throws IOException, WriterException {
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

		ImageIO.write(bufferedImage, "png", new File("./image.png"));
	}
}
