package team.gwon.haveameal.payment.mapper;

import team.gwon.haveameal.payment.dto.GetTiketPriceDto;

public interface PaymentMapper {

	int getTiketPrice(GetTiketPriceDto getTiketPriceDto);

	int amount(String impUid);

	int createPayment();
}
