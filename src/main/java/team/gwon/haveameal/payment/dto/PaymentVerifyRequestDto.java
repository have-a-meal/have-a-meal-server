package team.gwon.haveameal.payment.dto;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import com.siot.IamportRestClient.response.IamportResponse;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import team.gwon.haveameal.common.util.UuidProvider;
import team.gwon.haveameal.payment.entity.PaymentDetail;

@Getter
@ToString
@AllArgsConstructor
public class PaymentVerifyRequestDto {
	@NotBlank(message = "paymentId는 32자리의 Hex 코드 형태여야 합니다.")
	@Size(min = 32, max = 32, message = "paymentId는 32자리의 Hex 코드 형태여야 합니다.")
	private String paymentId;

	@NotBlank(message = "impUid는 필수 값입니다.")
	private String impUid;

	// private PortOnePaymentStatus message;

	public PaymentDetail toPaymentDetail(IamportResponse<com.siot.IamportRestClient.response.Payment> iamportResponse) {
		return PaymentDetail.builder()
			.paymentDetailId(UuidProvider.stringToByte(this.paymentId))
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
