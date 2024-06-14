package team.gwon.haveameal.ticket.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import team.gwon.haveameal.member.domain.MemberEntity;
import team.gwon.haveameal.payment.entity.PaymentWithCourseIncludeDetail;
import team.gwon.haveameal.ticket.domain.TicketEntity;
import team.gwon.haveameal.ticket.domain.TicketQuantityRequestDto;

@Mapper
public interface TicketMapper {

	List<PaymentWithCourseIncludeDetail> findAllMyTickets(MemberEntity member);

	Long getQrCode(PaymentWithCourseIncludeDetail paymentWithCourseIncludeDetail);

	Integer useQrCode(TicketEntity ticketEntity);

	int getMyTicketQuantity(TicketQuantityRequestDto ticketQuantityRequestDto);
}
