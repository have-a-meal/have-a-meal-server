package team.gwon.haveameal.register.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import team.gwon.haveameal.register.domain.MemberRegister;

@Repository
@Mapper
public interface MemberMapper {
	void insertMember(MemberRegister member);
}
