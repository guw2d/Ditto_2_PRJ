package com.ditto.service;

import java.util.List;
import java.util.Optional;

import com.ditto.dto.MemberDTO;
import com.ditto.dto.PageRequestDTO;
import com.ditto.dto.PageResultDTO;
import com.ditto.entity.S_Ditto_MemberEntity;

public interface MemberService {
	
	default S_Ditto_MemberEntity dtoToEntity(MemberDTO dto) {
		//DTO --> Entity 변환 메서드 정의합니다.
		S_Ditto_MemberEntity member = S_Ditto_MemberEntity.builder()
				.memberNo(dto.getMemberNo())
				.loginId(dto.getLoginId())
				.loginPw(dto.getLoginPw())
				.memberNm(dto.getMemberNm())
				.memberEmail(dto.getMemberEmail())
				.memberAddr(dto.getMemberAddr())
				.memberZipcd(dto.getMemberZipcd())
				.memberTel(dto.getMemberTel())
				.memberDt(dto.getMemberDt())
				.build();
		return member;
    }
	
	//Entity --> DTO 변환 메서드 정의
	default MemberDTO entityToDto(S_Ditto_MemberEntity member) {
		MemberDTO memberDTO = MemberDTO.builder()
				.memberNo(member.getMemberNo())
				.loginId(member.getLoginId())
				.loginPw(member.getLoginPw())
				.memberNm(member.getMemberNm())
				.memberEmail(member.getMemberEmail())
				.memberAddr(member.getMemberAddr())
				.memberZipcd(member.getMemberZipcd())
				.memberTel(member.getMemberTel())
				.memberDt(member.getMemberDt())
				.IDt(member.getIDt())
				.UDt(member.getUDt())
				.build();
		return memberDTO;
	}
	//신규 글 등록 메서드 선언.
	Long register(MemberDTO memberDTO);
	
	//수정
	void updateArticle(MemberDTO memberDTO);
	
	//삭제
	void delArticle(Long memberNo);
	
	//특정조회
	MemberDTO get(Long memberNo);
	
	//마스터 리스트 출력
	PageResultDTO<MemberDTO, S_Ditto_MemberEntity> m_getList(PageRequestDTO pageRequestDTO);
	
	//회원가입
	public boolean joinProcess(MemberDTO memberDto);
	
	//중복 id, pw 확인  
	public boolean authenticate(String loginId, String loginPw);

	List<S_Ditto_MemberEntity> getList();
	
	// 아이디로 회원 조회
    MemberDTO getMemberByLoginId(String loginId);
      
    Optional<S_Ditto_MemberEntity> findMemberIdByEmail(String memberEmail);

    Optional<String> findMemberPasswordByEmail(String memberEmail);

    // 아이디로 회원 조회 메서드 구현
	Optional<S_Ditto_MemberEntity> findMemberByLoginId(String loginId);

	// 회원 탈퇴 메서드 구현
	void deleteMember(String loginId, String loginPw);

}
