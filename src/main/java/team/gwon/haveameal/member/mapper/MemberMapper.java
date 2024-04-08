package team.gwon.haveameal.member.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import team.gwon.haveameal.member.domain.MemberRegister;

@Repository
@Mapper
public interface MemberMapper {
	void insertMember(MemberRegister member);
}
