package com.ditto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ditto.dto.S_ADTO;
import com.ditto.entity.S_Ditto_AEntity;
import com.ditto.repository.S_Ditto_ARepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class S_Ditto_AServiceImpl implements S_Ditto_AService {
	
	private final S_Ditto_ARepository aRepository;

	@Override
	public Long register(S_ADTO S_ADTO) {
		S_Ditto_AEntity entity = dtoToEntity(S_ADTO);
		S_Ditto_AEntity savedEntity = aRepository.save(entity);
		return savedEntity.getInquiry();
	}

	@Override
	public void updateArticle(S_ADTO S_ADTO) {
		Optional<S_Ditto_AEntity> res = aRepository.findById(S_ADTO.getInquiry());
		if(res.isPresent()) {
			S_Ditto_AEntity s_a = res.get();
			s_a.setQnaNo(S_ADTO.getQnaNo());
			s_a.setAnswerContent(S_ADTO.getAnswerContent());
			aRepository.save(s_a);
		}
		
	}

	@Override
	public void delArticle(Long S_ANo) {
		aRepository.deleteById(S_ANo);
		
	}

	@Override
	public S_ADTO get(Long S_ANo) {
		 Optional<S_Ditto_AEntity> optionalS_A = aRepository.findById(S_ANo);
		    if (optionalS_A.isPresent()) {
		    	S_Ditto_AEntity s_a = optionalS_A.get();
		        return entityToDto(s_a);
		    } else {
		        // 조회된 회원이 없을 경우 null이 아닌 다른 값이나 예외 처리를 해야 합니다.
		        throw new RuntimeException("해당하는 회원을 찾을 수 없습니다.");
		    }
	}

	@Override
	public List<S_Ditto_AEntity> getList() {
		return aRepository.findAll();
	}

}
