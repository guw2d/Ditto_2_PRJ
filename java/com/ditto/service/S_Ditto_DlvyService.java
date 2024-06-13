package com.ditto.service;

import java.util.List;

import com.ditto.dto.DlvyDTO;
import com.ditto.entity.S_Ditto_DlvyEntity;

public interface S_Ditto_DlvyService {
	
	default S_Ditto_DlvyEntity dtoToEntity(DlvyDTO dto) {
		//DTO --> Entity 변환 메서드 정의합니다.
		S_Ditto_DlvyEntity Dlvy = S_Ditto_DlvyEntity.builder()
				.invoiceNo(dto.getInvoiceNo())
				.ordNo(dto.getOrdNo())
				.deliveryId(dto.getDeliveryId())
				.exType(dto.getExType())
				.exDT(dto.getExDT())
				.build();
		return Dlvy;
    }
	
	//Entity --> DTO 변환 메서드 정의
	default DlvyDTO entityToDto(S_Ditto_DlvyEntity dto) {
		DlvyDTO dlvyDTO = DlvyDTO.builder()
				.invoiceNo(dto.getInvoiceNo())
				.ordNo(dto.getOrdNo())
				.deliveryId(dto.getDeliveryId())
				.exType(dto.getExType())
				.exDT(dto.getExDT())
				.build();
		return dlvyDTO;
	}
	//신규 글 등록 메서드 선언.
	Long register(DlvyDTO DlvyDTO);
	
	//수정
	void updateArticle(DlvyDTO DlvyDTO);
	
	//삭제
	void delArticle(Long DlvyNo);
	
	//특정조회
	DlvyDTO get(Long DlvyNo);
	
	//리스트 출력
	public List<S_Ditto_DlvyEntity> getList();

}
