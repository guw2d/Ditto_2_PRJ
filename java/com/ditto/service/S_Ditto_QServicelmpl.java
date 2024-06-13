package com.ditto.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ditto.dto.S_QDTO;
import com.ditto.entity.S_Ditto_QEntity;
import com.ditto.repository.S_Ditto_QRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class S_Ditto_QServicelmpl implements S_Ditto_QService {
	
	private final S_Ditto_QRepository qRepository;

	@Override
	public Long register(S_QDTO QDTO) {
		S_Ditto_QEntity entity = dtoToEntity(QDTO);
		S_Ditto_QEntity savedEntity = qRepository.save(entity);
		return savedEntity.getQnaNo();
	}

	@Override
	public void updateArticle(S_QDTO QDTO) {
		Optional<S_Ditto_QEntity> res = qRepository.findById(QDTO.getQnaNo());
		if(res.isPresent()) {
			S_Ditto_QEntity q = res.get();
			q.setInquiryTitle(QDTO.getInquiryTitle());
			q.setInquiryContent(QDTO.getInquiryContent());
			q.setInquiryStatus(QDTO.getInquiryStatus());
			qRepository.save(q);
		}
		
	}

	@Override
	public void delArticle(Long QNo) {
		qRepository.deleteById(QNo);
		
	}

	@Override
	public S_QDTO get(Long QNo) {
		 Optional<S_Ditto_QEntity> optionalQ = qRepository.findById(QNo);
		    if (optionalQ.isPresent()) {
		    	S_Ditto_QEntity q = optionalQ.get();
		        return entityToDto(q);
		    } else {
		        // 조회된 회원이 없을 경우 null이 아닌 다른 값이나 예외 처리를 해야 합니다.
		        throw new RuntimeException("해당하는 회원을 찾을 수 없습니다.");
		    }
	}

	@Override
	public List<S_Ditto_QEntity> getList() {
		// 데이터베이스에서 모든 엔티티를 가져옴
        List<S_Ditto_QEntity> list = qRepository.findAll();
        
        // 리스트를 역순으로 뒤집음
        Collections.reverse(list);
        
        // 역순으로 뒤집힌 리스트를 반환함
        return list;
	}
	
	
	
	
	

}
