package team.gwon.haveameal.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import team.gwon.haveameal.domain.Member;

@Repository
@Mapper
public interface MemberMapper {
	void insertMember(Member member);
}
