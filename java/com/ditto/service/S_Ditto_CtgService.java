package com.ditto.service;

import java.util.List;

import com.ditto.dto.CtgDTO;
import com.ditto.entity.S_Ditto_CtgEntity;

public interface S_Ditto_CtgService {
	
	default S_Ditto_CtgEntity dtoToEntity(CtgDTO dto) {
		//DTO --> Entity 변환 메서드 정의합니다.
		S_Ditto_CtgEntity Ctg = S_Ditto_CtgEntity.builder()
				.ctgCd(dto.getCtgCd())
				.idx(dto.getIdx())
				.ctgNm(dto.getCtgNm())
				.ctgCd(dto.getCtgCd())
				.build();
		return Ctg;
    }
	
	//Entity --> DTO 변환 메서드 정의
	default CtgDTO entityToDto(S_Ditto_CtgEntity dto) {
		CtgDTO ctgDTO = CtgDTO.builder()
				.ctgCd(dto.getCtgCd())
				.idx(dto.getIdx())
				.ctgNm(dto.getCtgNm())
				.ctgCd(dto.getCtgCd())
				.build();
		return ctgDTO;
	}
	//신규 글 등록 메서드 선언.
	Long register(CtgDTO CtgDTO);
	
	//수정
	void updateArticle(CtgDTO CtgDTO);
	
	//삭제
	void delArticle(Long CtgNo);
	
	//특정조회
	CtgDTO get(Long CtgNo);
	
	//리스트 출력
	public List<S_Ditto_CtgEntity> getList();

}
