package com.ditto.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ditto.dto.M_NotiDTO;
import com.ditto.dto.PageRequestDTO;
import com.ditto.dto.PageResultDTO;
import com.ditto.entity.M_Ditto_NotiEntity;
import com.ditto.entity.QM_Ditto_NotiEntity;
import com.ditto.repository.M_Ditto_NotiRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class M_Ditto_NotiServiceImpl implements M_Ditto_NotiService {
	
	private final M_Ditto_NotiRepository m_nostRepository;

	@Override
	public Long register(M_NotiDTO m_nostDTO) {//등록
		M_Ditto_NotiEntity entity = dtoToEntity(m_nostDTO);
		M_Ditto_NotiEntity savedEntity = m_nostRepository.save(entity);
		return savedEntity.getMQnaNo();
	}

	@Override
	public void updateArticle(M_NotiDTO m_nostDTO) {//수정
		Optional<M_Ditto_NotiEntity> res = m_nostRepository.findById(m_nostDTO.getMQnaNo());
		if(res.isPresent()) {
			M_Ditto_NotiEntity m_nost = res.get();
			m_nost.setMQnaTitle(m_nostDTO.getMQnaTitle());
			m_nost.setMQnaCntt(m_nostDTO.getMQnaCntt());
			m_nost.setMQnaDt(m_nostDTO.getMQnaDt());
			m_nost.setMQnaStatus(m_nostDTO.getMQnaStatus());
			m_nost.setMQnaQAns(m_nostDTO.getMQnaQAns());
			m_nost.setMQnaQIdDt(m_nostDTO.getMNotiUDt());

			m_nostRepository.save(m_nost);
		}
	}

	@Override
	public void delArticle(Long m_nostNo) {//삭제
		m_nostRepository.deleteById(m_nostNo);
	}

	@Override
	public M_NotiDTO get(Long m_nostNo) {//특정 조회
		 Optional<M_Ditto_NotiEntity> optionalM_Nost = m_nostRepository.findById(m_nostNo);
		    if (optionalM_Nost.isPresent()) {
		    	M_Ditto_NotiEntity m_nost = optionalM_Nost.get();
		    	m_nost.setMQnaCntt(null);
		        return entityToDto(m_nost);
		    } else {
		        // 조회된 회원이 없을 경우 null이 아닌 다른 값이나 예외 처리를 해야 합니다.
		        throw new RuntimeException("해당하는 회원을 찾을 수 없습니다.");
		    }
	}

	@Override
	public List<M_Ditto_NotiEntity> getList() {
		return m_nostRepository.findAll();
	}
	@Override
	public PageResultDTO<M_NotiDTO, M_Ditto_NotiEntity> m_getList(PageRequestDTO pageRequestDTO) {
		Pageable pageable = pageRequestDTO.getPageable(Sort.by("mQnaNo").descending());
		BooleanBuilder booleanBuilder = getSearch(pageRequestDTO);//etSearch 메서드는 검색 조건을 생성하는 역할
		Page<M_Ditto_NotiEntity> result = m_nostRepository.findAll(booleanBuilder, pageable);
		
		Function<M_Ditto_NotiEntity, M_NotiDTO> fn = this::entityToDto;
		
		return new PageResultDTO<>(result, fn);
	}
	
	private BooleanBuilder getSearch(PageRequestDTO requestDTO) {
		String type = requestDTO.getType();
		BooleanBuilder booleanBuilder = new BooleanBuilder();

		QM_Ditto_NotiEntity Noti = QM_Ditto_NotiEntity.m_Ditto_NotiEntity;

		String keyword = requestDTO.getKeyword();

		BooleanExpression exp = Noti.mQnaNo.gt(0L); // 1번 글 이상부터 검색 대상으로 시작함
		booleanBuilder.and(exp);

		if (type == null || keyword.isEmpty()) { // 검색 조건이 없음..
		    return booleanBuilder;
		}

		// 검색조건 작성
		BooleanBuilder booleanBuilder2 = new BooleanBuilder();

	
		booleanBuilder2.or(Noti.mQnaTitle.contains(keyword));
		


		// 위 조건 모두 통합
		booleanBuilder.and(booleanBuilder2);

		return booleanBuilder;
	}
}
