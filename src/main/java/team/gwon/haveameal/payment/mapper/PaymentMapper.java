package team.gwon.haveameal.payment.mapper;

import org.apache.ibatis.annotations.Mapper;

import team.gwon.haveameal.payment.entity.CourseWithDetail;
import team.gwon.haveameal.payment.entity.TicketPrice;

@Mapper
public interface PaymentMapper {

	TicketPrice getTicketPrice(CourseWithDetail courseWithDetail);

	int amount(String impUid);

	int createPayment();
}
