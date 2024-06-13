package com.ditto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ditto.dto.S_NotiDTO;
import com.ditto.entity.S_Ditto_NotiEntity;
import com.ditto.repository.S_Ditto_NotiRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class S_Ditto_NotiServiceImpl implements S_Ditto_NotiService {
	
	private final S_Ditto_NotiRepository notiRepository;

	@Override
	public Long register(S_NotiDTO S_NotiDTO) {
		S_Ditto_NotiEntity entity = dtoToEntity(S_NotiDTO);
		S_Ditto_NotiEntity savedEntity = notiRepository.save(entity);
		return savedEntity.getSNotiSeq();
	}

	@Override
	public void updateArticle(S_NotiDTO S_NotiDTO) {
		Optional<S_Ditto_NotiEntity> res = notiRepository.findById(S_NotiDTO.getSNotiSeq());
		if(res.isPresent()) {
			S_Ditto_NotiEntity noti = res.get();
			noti.setSNotiNm(S_NotiDTO.getSNotiNm());
			noti.setSNotiTitle(S_NotiDTO.getSNotiTitle());
			noti.setSNotiCntt(S_NotiDTO.getSNotiCntt());
			notiRepository.save(noti);
		}
		
	}

	@Override
	public void delArticle(Long S_NotiNo) {
		notiRepository.deleteById(S_NotiNo);
		
	}

	@Override
	public S_NotiDTO get(Long sNotiSeq) {
		 Optional<S_Ditto_NotiEntity> optionalS_Noti = notiRepository.findById(sNotiSeq);
		    if (optionalS_Noti.isPresent()) {
		    	S_Ditto_NotiEntity noti = optionalS_Noti.get();
		        return entityToDto(noti);
		    } else {
		        // 조회된 회원이 없을 경우 null이 아닌 다른 값이나 예외 처리를 해야 합니다.
		        throw new RuntimeException("해당하는 회원을 찾을 수 없습니다.");
		    }
	}

	@Override
	public List<S_Ditto_NotiEntity> getList() {
		return notiRepository.findAll();
	}

}
