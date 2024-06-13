package com.ditto.service;

import java.util.List;

import com.ditto.dto.S_ADTO;
import com.ditto.entity.S_Ditto_AEntity;


public interface S_Ditto_AService {
	
	default S_Ditto_AEntity dtoToEntity(S_ADTO dto) {
		//DTO --> Entity 변환 메서드 정의합니다.
		S_Ditto_AEntity S_A = S_Ditto_AEntity.builder()
				.inquiry(dto.getInquiry())
				.qnaNo(dto.getQnaNo())
				.answerContent(dto.getAnswerContent())
				.answerDate(dto.getAnswerDate())
				.build();
		return S_A;
    }
	
	//Entity --> DTO 변환 메서드 정의
	default S_ADTO entityToDto(S_Ditto_AEntity dto) {
		S_ADTO s_aDTO = S_ADTO.builder()
				.inquiry(dto.getInquiry())
				.qnaNo(dto.getQnaNo())
				.answerContent(dto.getAnswerContent())
				.answerDate(dto.getAnswerDate())
				.build();
		return s_aDTO;
	}
	//신규 글 등록 메서드 선언.
	Long register(S_ADTO S_ADTO);
	
	//수정
	void updateArticle(S_ADTO S_ADTO);
	
	//삭제
	void delArticle(Long S_ANo);
	
	//특정조회
	S_ADTO get(Long S_ANo);
	
	//리스트 출력
	public List<S_Ditto_AEntity> getList();

}
