package com.ditto.service;

import java.util.List;

import com.ditto.dto.M_NotiDTO;
import com.ditto.dto.PageRequestDTO;
import com.ditto.dto.PageResultDTO;
import com.ditto.entity.M_Ditto_NotiEntity;


public interface M_Ditto_NotiService {
	
	default M_Ditto_NotiEntity dtoToEntity(M_NotiDTO dto) {
		//DTO --> Entity 변환 메서드 정의합니다.
		M_Ditto_NotiEntity S_A = M_Ditto_NotiEntity.builder()
				.mQnaNo(dto.getMQnaNo())
				.mQnaTitle(dto.getMQnaTitle())
				.mQnaCntt(dto.getMQnaCntt())
				.mQnaDt(dto.getMQnaDt())
				.mQnaStatus(dto.getMQnaStatus())
				.mQnaQAns(dto.getMQnaQAns())
				.mQnaDt(dto.getMQnaDt())

				.build();
		return S_A;
    }
	
	//Entity --> DTO 변환 메서드 정의
	default M_NotiDTO entityToDto(M_Ditto_NotiEntity dto) {
		M_NotiDTO M_Noti = M_NotiDTO.builder()
				.mQnaNo(dto.getMQnaNo())
				.mQnaTitle(dto.getMQnaTitle())
				.mQnaCntt(dto.getMQnaCntt())
				.mQnaDt(dto.getMQnaDt())
				.mQnaStatus(dto.getMQnaStatus())
				.mQnaQAns(dto.getMQnaQAns())
				.mQnaDt(dto.getMQnaDt())

				.build();
		return M_Noti;
	}
	//신규 글 등록 메서드 선언.
	Long register(M_NotiDTO M_NotiDTO);
	
	//수정
	void updateArticle(M_NotiDTO M_NotiDTO);
	
	//삭제
	void delArticle(Long S_ANo);
	
	//특정조회
	M_NotiDTO get(Long S_ANo);
	
	//리스트 출력
	public List<M_Ditto_NotiEntity> getList();
	
	//페이지 리스트 출력
	PageResultDTO<M_NotiDTO, M_Ditto_NotiEntity> m_getList(PageRequestDTO pageRequestDTO);

}
