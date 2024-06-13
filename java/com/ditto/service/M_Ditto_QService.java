package com.ditto.service;

import java.util.List;

import com.ditto.dto.M_QDTO;
import com.ditto.dto.MemberDTO;
import com.ditto.dto.PageRequestDTO;
import com.ditto.dto.PageResultDTO;
import com.ditto.entity.M_Ditto_QEntity;
import com.ditto.entity.S_Ditto_MemberEntity;



public interface M_Ditto_QService {
	
	default M_Ditto_QEntity dtoToEntity(M_QDTO dto) {
		//DTO --> Entity 변환 메서드 정의합니다.
		M_Ditto_QEntity S_A = M_Ditto_QEntity.builder()
				.mQnaNo(dto.getMQnaNo())
				.loginId(dto.getLoginId())
				.mQnaTitle(dto.getMQnaTitle())
				.mQnaContent(dto.getMQnaContent())
				.mQnaStatus(dto.getMQnaStatus())
				.mQnaAnswer(dto.getMQnaAnswer())
				.build();
		return S_A;
    }
	
	//Entity --> DTO 변환 메서드 정의
	default M_QDTO entityToDto(M_Ditto_QEntity dto) {
		M_QDTO M_Noti = M_QDTO.builder()
				.mQnaNo(dto.getMQnaNo())
				.loginId(dto.getLoginId())
				.mQnaTitle(dto.getMQnaTitle())
				.mQnaContent(dto.getMQnaContent())
				.mQnaStatus(dto.getMQnaStatus())
				.mQnaAnswer(dto.getMQnaAnswer())
				.build();
		return M_Noti;
	}
	//신규 글 등록 메서드 선언.
	Long register(M_QDTO M_NotiDTO);
	
	//수정
	void updateArticle(M_QDTO M_NotiDTO);
	
	//삭제
	void delArticle(Long S_ANo);
	
	//특정조회
	M_QDTO get(Long S_ANo);
	
	//리스트 출력
	public List<M_Ditto_QEntity> getList();

	//페이지 리스트 출력
	PageResultDTO<M_QDTO, M_Ditto_QEntity> m_getList(PageRequestDTO pageRequestDTO);

}
