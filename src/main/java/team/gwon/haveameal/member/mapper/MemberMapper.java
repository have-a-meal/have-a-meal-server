package team.gwon.haveameal.member.mapper;

import org.apache.ibatis.annotations.Mapper;

import team.gwon.haveameal.member.domain.MemberRegisterDto;

@Mapper
public interface MemberMapper {
	void insertMember(MemberRegisterDto member);
}
