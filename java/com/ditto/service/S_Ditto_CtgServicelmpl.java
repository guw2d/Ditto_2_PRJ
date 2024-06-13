package com.ditto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ditto.dto.CtgDTO;
import com.ditto.entity.S_Ditto_CtgEntity;
import com.ditto.repository.S_Ditto_CtgRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class S_Ditto_CtgServicelmpl implements S_Ditto_CtgService {
	
	private final S_Ditto_CtgRepository ctgRepository;

	@Override
	public Long register(CtgDTO CtgDTO) {
		S_Ditto_CtgEntity entity = dtoToEntity(CtgDTO);
		S_Ditto_CtgEntity savedEntity = ctgRepository.save(entity);
		return savedEntity.getCtgCd();
	}

	@Override
	public void updateArticle(CtgDTO CtgDTO) {
		Optional<S_Ditto_CtgEntity> res = ctgRepository.findById(CtgDTO.getCtgCd());
		if(res.isPresent()) {
			S_Ditto_CtgEntity ctg = res.get();
			ctg.setCtgNm(CtgDTO.getCtgNm());
			ctgRepository.save(ctg);
		}
		
	}

	@Override
	public void delArticle(Long CtgNo) {
		ctgRepository.deleteById(CtgNo);
		
	}

	@Override
	public CtgDTO get(Long CtgNo) {
		 Optional<S_Ditto_CtgEntity> optionalCtg = ctgRepository.findById(CtgNo);
		    if (optionalCtg.isPresent()) {
		    	S_Ditto_CtgEntity ctg = optionalCtg.get();
		        return entityToDto(ctg);
		    } else {
		        // 조회된 회원이 없을 경우 null이 아닌 다른 값이나 예외 처리를 해야 합니다.
		        throw new RuntimeException("해당하는 회원을 찾을 수 없습니다.");
		    }
	}

	@Override
	public List<S_Ditto_CtgEntity> getList() {
		return ctgRepository.findAll();
	}

}
