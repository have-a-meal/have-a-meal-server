package team.gwon.haveameal.payment.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import team.gwon.haveameal.payment.entity.CourseWithDetail;
import team.gwon.haveameal.payment.entity.Payment;
import team.gwon.haveameal.payment.entity.PaymentDetail;
import team.gwon.haveameal.payment.entity.PaymentWithCourseIncludeDetail;
import team.gwon.haveameal.payment.entity.PaymentWithDetail;
import team.gwon.haveameal.payment.entity.TicketPrice;

@Mapper
public interface PaymentMapper {

	TicketPrice getTicketPrice(CourseWithDetail courseWithDetail);

	int amount(String impUid);

	int createPayment(Payment payment);

	int createPaymentDetail(PaymentDetail paymentDetail);

	List<PaymentWithDetail> getPaymentWithDetail(String memberId);

	List<CourseWithDetail> getCourseWithDetail();

	List<PaymentWithCourseIncludeDetail> getPaymentTransaction(String memberId);
}
