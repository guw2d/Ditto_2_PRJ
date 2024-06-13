package com.ditto.service;

import java.util.List;

import com.ditto.dto.S_NotiDTO;
import com.ditto.entity.S_Ditto_NotiEntity;

public interface S_Ditto_NotiService {
	
	default S_Ditto_NotiEntity dtoToEntity(S_NotiDTO dto) {
		//DTO --> Entity 변환 메서드 정의합니다.
		S_Ditto_NotiEntity Noti = S_Ditto_NotiEntity.builder()
				.sNotiSeq(dto.getSNotiSeq())
				.sNotiNm(dto.getSNotiNm())
				.sNotiTitle(dto.getSNotiTitle())
				.sNotiCntt(dto.getSNotiCntt())
				.sNotiIDt(dto.getSNotiIDt())
				.sNotiUDt(dto.getSNotiUDt())
				.build();
		return Noti;
    }
	
	//Entity --> DTO 변환 메서드 정의
	default S_NotiDTO entityToDto(S_Ditto_NotiEntity dto) {
		S_NotiDTO notiDTO = S_NotiDTO.builder()
				.sNotiSeq(dto.getSNotiSeq())
				.sNotiNm(dto.getSNotiNm())
				.sNotiTitle(dto.getSNotiTitle())
				.sNotiCntt(dto.getSNotiCntt())
				.sNotiIDt(dto.getSNotiIDt())
				.sNotiUDt(dto.getSNotiUDt())
				.build();
		return notiDTO;
	}
	//신규 글 등록 메서드 선언.
	Long register(S_NotiDTO NotiDTO);
	
	//수정
	void updateArticle(S_NotiDTO NotiDTO);
	
	//삭제
	void delArticle(Long NotiNo);
	
	//특정조회
	S_NotiDTO get(Long NotiNo);
	
	//리스트 출력
	public List<S_Ditto_NotiEntity> getList();

}
