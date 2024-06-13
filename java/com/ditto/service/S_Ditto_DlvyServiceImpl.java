package com.ditto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ditto.dto.DlvyDTO;
import com.ditto.entity.S_Ditto_DlvyEntity;
import com.ditto.repository.S_Ditto_DlvyRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class S_Ditto_DlvyServiceImpl implements S_Ditto_DlvyService {
	
	private final S_Ditto_DlvyRepository dlvyRepository;

	@Override
	public Long register(DlvyDTO DlvyDTO) {
		S_Ditto_DlvyEntity entity = dtoToEntity(DlvyDTO);
		S_Ditto_DlvyEntity savedEntity = dlvyRepository.save(entity);
		return savedEntity.getInvoiceNo();
	}

	@Override
	public void updateArticle(DlvyDTO DlvyDTO) {
		Optional<S_Ditto_DlvyEntity> res = dlvyRepository.findById(DlvyDTO.getInvoiceNo());
		if(res.isPresent()) {
			S_Ditto_DlvyEntity dlvy = res.get();
			dlvy.setDeliveryId(DlvyDTO.getDeliveryId());
			dlvy.setExType(DlvyDTO.getExType());
			dlvyRepository.save(dlvy);
		}
		
	}

	@Override
	public void delArticle(Long DlvyNo) {
		dlvyRepository.deleteById(DlvyNo);
		
	}

	@Override
	public DlvyDTO get(Long DlvyNo) {
		 Optional<S_Ditto_DlvyEntity> optionalDlvy = dlvyRepository.findById(DlvyNo);
		    if (optionalDlvy.isPresent()) {
		    	S_Ditto_DlvyEntity dlvy = optionalDlvy.get();
		        return entityToDto(dlvy);
		    } else {
		        // 조회된 회원이 없을 경우 null이 아닌 다른 값이나 예외 처리를 해야 합니다.
		        throw new RuntimeException("해당하는 회원을 찾을 수 없습니다.");
		    }
	}

	@Override
	public List<S_Ditto_DlvyEntity> getList() {
		return dlvyRepository.findAll();
	}

}
