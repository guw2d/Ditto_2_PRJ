package com.ditto.service;

import java.util.List;

import com.ditto.dto.Prod_ImgDTO;
import com.ditto.entity.S_Ditto_Prod_ImgEntity;

public interface S_Ditto_Prod_ImgService {
	default S_Ditto_Prod_ImgEntity dtoToEntity(Prod_ImgDTO dto) {
	    //DTO --> Entity 변환 메서드 정의합니다.
	    S_Ditto_Prod_ImgEntity prod_img = S_Ditto_Prod_ImgEntity.builder()
	            .imgNo(dto.getImgNo())
	            .path(dto.getPath())
	            .imgIDt(dto.getImgIDt())
	            .imgUDt(dto.getImgUDt())
	            .build();
	    return prod_img;
	}

	//Entity --> DTO 변환 메서드 정의
	default Prod_ImgDTO entityToDto(S_Ditto_Prod_ImgEntity prod_img) {
	    Prod_ImgDTO prod_imgDTO = Prod_ImgDTO.builder()
	            .imgNo(prod_img.getImgNo())
	            .path(prod_img.getPath())
	            .imgIDt(prod_img.getImgIDt())
	            .imgUDt(prod_img.getImgUDt())
	            .build();
	    return prod_imgDTO;
	}
	//신규 글 등록 메서드 선언.
	Long register(Prod_ImgDTO prod_imgDTO);

	//수정
	void updateArticle(Prod_ImgDTO prod_imgDTO);

	//삭제
	void delArticle(Long prod_imgNo);

	//특정조회
	Prod_ImgDTO get(Long prod_imgNo);

	//리스트 출력
	public List<S_Ditto_Prod_ImgEntity> getList();
}
