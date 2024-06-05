package team.gwon.haveameal.payment.dto;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import com.siot.IamportRestClient.response.IamportResponse;

import lombok.Getter;
import team.gwon.haveameal.payment.entity.PaymentDetail;

@Getter
public class PaymentVerifyRequestDto {
	private byte[] paymentId;
	private String impUid;

	// private PortOnePaymentStatus message;

	public PaymentDetail toPaymentDetail(IamportResponse<com.siot.IamportRestClient.response.Payment> iamportResponse) {
		return PaymentDetail.builder()
			.paymentDetailId(this.paymentId)
			.status(iamportResponse.getResponse().getStatus())
			.impUid(this.impUid)
			.pgTid(iamportResponse.getResponse().getPgTid())
			.paidAt(toLocalDateTime(iamportResponse.getResponse().getPaidAt())).build();
	}

	public LocalDateTime toLocalDateTime(Date date) {
		return Instant.ofEpochMilli(date.getTime())
			.atZone(ZoneId.systemDefault())
			.toLocalDateTime();
	}
}
