package team.gwon.haveameal.ticket.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import team.gwon.haveameal.member.domain.MemberEntity;
import team.gwon.haveameal.payment.entity.PaymentWithCourseIncludeDetail;

@Mapper
public interface TicketMapper {

	List<PaymentWithCourseIncludeDetail> findAllMyTickets(MemberEntity member);
}
