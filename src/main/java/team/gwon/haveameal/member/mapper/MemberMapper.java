package team.gwon.haveameal.member.mapper;

import org.apache.ibatis.annotations.Mapper;

import team.gwon.haveameal.member.domain.MemberEntity;
import team.gwon.haveameal.member.domain.MemberFindDto;

@Mapper
public interface MemberMapper {
	void insertMember(MemberEntity member);

	MemberFindDto getMemberById(String memberId);
}
