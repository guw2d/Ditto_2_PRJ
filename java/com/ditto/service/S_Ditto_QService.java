package com.ditto.service;

import java.util.List;

import com.ditto.dto.S_QDTO;
import com.ditto.entity.S_Ditto_QEntity;

public interface S_Ditto_QService {
	
	default S_Ditto_QEntity dtoToEntity(S_QDTO dto) {
		//DTO --> Entity 변환 메서드 정의합니다.
		S_Ditto_QEntity S_Q = S_Ditto_QEntity.builder()
				.qnaNo(dto.getQnaNo())
				.productId(dto.getProductId())
				.productName(dto.getProductName())
				.loginId(dto.getLoginId())
				.inquiryTitle(dto.getInquiryTitle())
				.inquiryContent(dto.getInquiryContent())
				.orderNo(dto.getOrderNo())
				.inquiryStatus(dto.getInquiryStatus())
				.build();
		return S_Q;
    }
	
	//Entity --> DTO 변환 메서드 정의
	default S_QDTO entityToDto(S_Ditto_QEntity dto) {
		S_QDTO s_qDTO = S_QDTO.builder()
				.qnaNo(dto.getQnaNo())
				.productId(dto.getProductId())
				.productName(dto.getProductName())
				.loginId(dto.getLoginId())
				.inquiryTitle(dto.getInquiryTitle())
				.inquiryContent(dto.getInquiryContent())
				.orderNo(dto.getOrderNo())
				.inquiryStatus(dto.getInquiryStatus())
				.build();
		return s_qDTO;
	}
	//신규 글 등록 메서드 선언.
	Long register(S_QDTO S_QDTO);
	
	//수정
	void updateArticle(S_QDTO S_QDTO);
	
	//삭제
	void delArticle(Long S_QNo);
	
	//특정조회
	S_QDTO get(Long S_QNo);
	
	//리스트 출력
	public List<S_Ditto_QEntity> getList();

}
