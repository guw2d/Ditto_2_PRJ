package com.ditto.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ditto.dto.M_QDTO;
import com.ditto.dto.MemberDTO;
import com.ditto.dto.PageRequestDTO;
import com.ditto.dto.PageResultDTO;
import com.ditto.entity.M_Ditto_QEntity;
import com.ditto.entity.QM_Ditto_QEntity;
import com.ditto.entity.QS_Ditto_MemberEntity;
import com.ditto.entity.S_Ditto_MemberEntity;
import com.ditto.repository.M_Ditto_QRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class M_Ditto_QServiceImpl implements M_Ditto_QService {
	
	private final M_Ditto_QRepository qRepository;

	@Override
	public Long register(M_QDTO qDTO) {//등록
		M_Ditto_QEntity entity = dtoToEntity(qDTO);
		M_Ditto_QEntity savedEntity = qRepository.save(entity);
		return savedEntity.getMQnaNo();
	}

	@Override
	public void updateArticle(M_QDTO qDTO) {//수정
		Optional<M_Ditto_QEntity> res = qRepository.findById(qDTO.getMQnaNo());
		if(res.isPresent()) {
			M_Ditto_QEntity q = res.get();
			q.setMQnaNo(qDTO.getMQnaNo());//
			q.setMQnaTitle(qDTO.getMQnaTitle());
			q.setMQnaContent(qDTO.getMQnaContent());
			q.setMQnaDate(qDTO.getMQnaDate());
			q.setMQnaAnswer(qDTO.getMQnaAnswer());
			q.setMQnaStatus(qDTO.getMQnaStatus());
			q.setMQnaDate(qDTO.getMQnaDate());
			System.out.println(q);
			qRepository.save(q);
		}
	}

	@Override
	public void delArticle(Long qNo) {//삭제
		qRepository.deleteById(qNo);
	}

	@Override
	public M_QDTO get(Long qNo) {//특정 조회
		 Optional<M_Ditto_QEntity> optionalQ = qRepository.findById(qNo);
		    if (optionalQ.isPresent()) {
		    	M_Ditto_QEntity q = optionalQ.get();
		        return entityToDto(q);
		    } else {
		        // 조회된 회원이 없을 경우 null이 아닌 다른 값이나 예외 처리를 해야 합니다.
		        throw new RuntimeException("해당하는 회원을 찾을 수 없습니다.");
		    }
	}

	@Override
	public List<M_Ditto_QEntity> getList() {
		return qRepository.findAll();
	}

	@Override
	public PageResultDTO<M_QDTO, M_Ditto_QEntity> m_getList(PageRequestDTO pageRequestDTO) {
		Pageable pageable = pageRequestDTO.getPageable(Sort.by("mQnaNo").descending());
		BooleanBuilder booleanBuilder = getSearch(pageRequestDTO);//etSearch 메서드는 검색 조건을 생성하는 역할
		Page<M_Ditto_QEntity> result = qRepository.findAll(booleanBuilder, pageable);
		
		Function<M_Ditto_QEntity, M_QDTO> fn = this::entityToDto;
		
		return new PageResultDTO<>(result, fn);
	}
	
	private BooleanBuilder getSearch(PageRequestDTO requestDTO) {
		String type = requestDTO.getType();
		BooleanBuilder booleanBuilder = new BooleanBuilder();

		QM_Ditto_QEntity qS_Ditto_Q = QM_Ditto_QEntity.m_Ditto_QEntity;

		String keyword = requestDTO.getKeyword();

		BooleanExpression exp = qS_Ditto_Q.mQnaNo.gt(0L); // 1번 글 이상부터 검색 대상으로 시작함
		booleanBuilder.and(exp);

		if (type == null || keyword.isEmpty()) { // 검색 조건이 없음..
		    return booleanBuilder;
		}

		// 검색조건 작성
		BooleanBuilder booleanBuilder2 = new BooleanBuilder();

		if ("l".equals(type)) { // 유저 아이디으로 검색을 요청했을 경우
		    booleanBuilder2.or(qS_Ditto_Q.loginId.contains(keyword));
		}
		if ("t".equals(type)) { // 제목으로 검색 요청시
		    booleanBuilder2.or(qS_Ditto_Q.mQnaTitle.contains(keyword));
		}
		

		// 위 조건 모두 통합
		booleanBuilder.and(booleanBuilder2);

		return booleanBuilder;
	}

}
