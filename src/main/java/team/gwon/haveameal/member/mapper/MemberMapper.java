package team.gwon.haveameal.member.mapper;

import org.apache.ibatis.annotations.Mapper;

import team.gwon.haveameal.member.domain.MemberEntity;

@Mapper
public interface MemberMapper {
	void insertMember(MemberEntity member);

	MemberEntity getMemberById(String memberId);
}
