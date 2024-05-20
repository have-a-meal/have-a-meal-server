package team.gwon.haveameal.payment.entity;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class TicketPrice {
	int courseId;
	int courseDetailId;
	int price;
}
