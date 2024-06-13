package com.ditto.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.ditto.dto.GetProdDTO;
import com.ditto.dto.MemberDTO;
import com.ditto.dto.PageRequestDTO;
import com.ditto.dto.PageResultDTO;
import com.ditto.dto.ProdDTO;
import com.ditto.entity.Get_ProdEntiy;
import com.ditto.entity.M_Ditto_QEntity;
import com.ditto.entity.QGet_ProdEntiy;
import com.ditto.entity.QS_Ditto_MemberEntity;
import com.ditto.entity.S_Ditto_MemberEntity;
import com.ditto.entity.S_Ditto_ProdEntity;
import com.ditto.entity.S_Ditto_Prod_ImgEntity;
import com.ditto.repository.Get_ProdRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class Get_ProdServicelmpl implements Get_ProdService{
	private final Get_ProdRepository prodRepository;
	
	@Override
    public Page<Get_ProdEntiy> findAllProducts(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        System.out.println(pageable);
        return prodRepository.findAll(pageable);
    }

    
    
    
    
	@Override
	public Long register(GetProdDTO prodDTO) {
		Get_ProdEntiy entity = dtoToEntity(prodDTO);
		Get_ProdEntiy savedEntity = prodRepository.save(entity);
		return savedEntity.getProdId();
	}

	@Override
	public GetProdDTO get(Long prodId) {//특정 조회
		
		 Optional<Get_ProdEntiy> prodEntiy = prodRepository.findById(prodId);
		    if (prodEntiy.isPresent()) {
		    	Get_ProdEntiy q = prodEntiy.get();
		        return entityToDto(q);
		    } else {
		        // 조회된 회원이 없을 경우 null이 아닌 다른 값이나 예외 처리를 해야 합니다.
		        throw new RuntimeException("해당하는 회원을 찾을 수 없습니다.");
		    }
		
	}


	@Override
	public List<Get_ProdEntiy> getList() {
		return prodRepository.findAll();
	}





	@Override
	public PageResultDTO<GetProdDTO, Get_ProdEntiy> m_getList(PageRequestDTO pageRequestDTO) {
		Pageable pageable = pageRequestDTO.getPageable(Sort.by("prodId").descending());
		BooleanBuilder booleanBuilder = getSearch(pageRequestDTO);//etSearch 메서드는 검색 조건을 생성하는 역할
		Page<Get_ProdEntiy> result = prodRepository.findAll(booleanBuilder, pageable);
		
		Function<Get_ProdEntiy, GetProdDTO> fn = this::entityToDto;
		
		return new PageResultDTO<>(result, fn);
	}

	private BooleanBuilder getSearch(PageRequestDTO requestDTO) {
		String type = requestDTO.getType();
		BooleanBuilder booleanBuilder = new BooleanBuilder();

		QGet_ProdEntiy qGet_ProdEntiy = QGet_ProdEntiy.get_ProdEntiy;

		String keyword = requestDTO.getKeyword();

		BooleanExpression exp = qGet_ProdEntiy.prodId.gt(0L); // 1번 글 이상부터 검색 대상으로 시작함
		booleanBuilder.and(exp);

		if (type == null || keyword.isEmpty()) { // 검색 조건이 없음..
		    return booleanBuilder;
		}

		// 검색조건 작성
		BooleanBuilder booleanBuilder2 = new BooleanBuilder();


		booleanBuilder2.or(qGet_ProdEntiy.prodNm.contains(keyword));
	

		// 위 조건 모두 통합
		booleanBuilder.and(booleanBuilder2);

		return booleanBuilder;
	}
}
