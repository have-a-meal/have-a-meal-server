package team.gwon.haveameal.payment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PaymentVerifyDto {
	private String impUid;
	private String name;
	// ready(브라우저 창 이탈, 가상계좌 발급 완료 등 미결제 상태)
	// paid(결제완료)
	// failed(신용카드 한도 초과, 체크카드 잔액 부족, 브라우저 창 종료 또는 취소 버튼 클릭 등 결제실패 상태)
	private boolean status;
	private Long amount;
}
