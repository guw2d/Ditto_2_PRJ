package com.ditto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ditto.dto.Prod_ImgDTO;
import com.ditto.entity.S_Ditto_Prod_ImgEntity;
import com.ditto.repository.S_Ditto_Prod_ImgRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class S_Ditto_Prod_ImgServicelmpl implements S_Ditto_Prod_ImgService{

	private final S_Ditto_Prod_ImgRepository prod_imgRepository;

	@Override
	public Long register(Prod_ImgDTO prod_imgDTO) {//등록
		S_Ditto_Prod_ImgEntity entity = dtoToEntity(prod_imgDTO);
		S_Ditto_Prod_ImgEntity savedEntity = prod_imgRepository.save(entity);
		return savedEntity.getImgNo();
	}

	@Override
	public void updateArticle(Prod_ImgDTO prod_imgDTO) {//수정
		Optional<S_Ditto_Prod_ImgEntity> res = prod_imgRepository.findById(prod_imgDTO.getImgNo());
		if(res.isPresent()) {
			S_Ditto_Prod_ImgEntity prod_img = res.get();
			prod_img.setPath(prod_imgDTO.getPath());
			prod_imgRepository.save(prod_img);
		}
	}

	@Override
	public void delArticle(Long prod_imgNo) {//삭제
		prod_imgRepository.deleteById(prod_imgNo);
	}

	@Override
	public Prod_ImgDTO get(Long prod_imgNo) {//특정 조회
		 Optional<S_Ditto_Prod_ImgEntity> optionalProd_Img = prod_imgRepository.findById(prod_imgNo);
		    if (optionalProd_Img.isPresent()) {
		    	S_Ditto_Prod_ImgEntity prod_img = optionalProd_Img.get();
		        return entityToDto(prod_img);
		    } else {
		        // 조회된 회원이 없을 경우 null이 아닌 다른 값이나 예외 처리를 해야 합니다.
		        throw new RuntimeException("해당하는 회원을 찾을 수 없습니다.");
		    }
	}

	@Override
	public List<S_Ditto_Prod_ImgEntity> getList() {
		return prod_imgRepository.findAll();
	}

}
