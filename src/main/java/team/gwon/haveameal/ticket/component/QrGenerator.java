package team.gwon.haveameal.ticket.component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Component;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import team.gwon.haveameal.common.util.TokenProvider;

@Component
public class QrGenerator {

	public byte[] generateQrImage(Integer ticketId, int width, int height) throws WriterException, IOException {
		String content = TokenProvider.generateToken(ticketId).getAccessToken();
		BitMatrix bitMatrix = new MultiFormatWriter()
			.encode(content, BarcodeFormat.QR_CODE, width, height);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream);

		return outputStream.toByteArray();
	}
}

