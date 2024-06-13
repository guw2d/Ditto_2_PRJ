package com.ditto.service;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.ditto.dto.PageRequestDTO;
import com.ditto.dto.PageResultDTO;
import com.ditto.dto.ProdDTO;
import com.ditto.entity.QS_Ditto_ProdEntity;
import com.ditto.entity.S_Ditto_ProdEntity;
import com.ditto.entity.S_Ditto_Prod_ImgEntity;
import com.ditto.repository.S_Ditto_ProdRepository;
import com.ditto.repository.S_Ditto_ProdRepository2;
import com.ditto.repository.S_Ditto_Prod_ImgRepository;
import com.ditto.repository.S_Ditto_Prod_ImgRepository2;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.RequiredArgsConstructor;




@Service
@RequiredArgsConstructor
public class S_Ditto_ProdServicelmpl implements S_Ditto_ProdService {

	private final S_Ditto_ProdRepository prodRepository;
	private final S_Ditto_ProdRepository2 prodRepository2;
	private final S_Ditto_Prod_ImgRepository prodImgRepository;
	private final S_Ditto_Prod_ImgRepository2 prodImgRepository2;
	@Transactional
	@Override
	public Long register(ProdDTO prodDTO) {//등록
		
		System.out.println(prodDTO);
		//0. MAP
		Map<String, Object> convertMap = dtoToEntity(prodDTO);
		
		//1. prod
		S_Ditto_ProdEntity prod = (S_Ditto_ProdEntity) convertMap.get("product");
		
		
		//2. img
		List<S_Ditto_Prod_ImgEntity> imgs = (List<S_Ditto_Prod_ImgEntity>) convertMap.get("imgList");
		
		prodRepository.save(prod);
		
		imgs.forEach(imgEntity -> {
			prodImgRepository.save(imgEntity);
		});
		
		
		return prod.getProdId();
		
	}
	@Override
	public PageResultDTO<ProdDTO, S_Ditto_ProdEntity> m_getList(PageRequestDTO pageRequestDTO) {
		Pageable pageable = pageRequestDTO.getPageable(Sort.by("prodId").descending());
		BooleanBuilder booleanBuilder = getSearch(pageRequestDTO);//etSearch 메서드는 검색 조건을 생성하는 역할
		Page<S_Ditto_ProdEntity> result = prodRepository.findAll(booleanBuilder, pageable);
		
		Function<S_Ditto_ProdEntity, ProdDTO> fn = this::entityToDto2;
		
		return new PageResultDTO<>(result, fn);
	}
	
	@Override
	public void updateArticle(ProdDTO prodDTO) {//수정
		Optional<S_Ditto_ProdEntity> res = prodRepository.findById(prodDTO.getProdId());
		if(res.isPresent()) {
			S_Ditto_ProdEntity prod = res.get();
			prod.setProdNm(prodDTO.getProdNm());
			prod.setOriginPrice(prodDTO.getOriginPrice());
			prod.setSalePrice(prodDTO.getSalePrice());
			prod.setSaleStatus(prodDTO.getSaleStatus());
			prod.setMargin(prodDTO.getMargin());
			prod.setTaxTp(prodDTO.getTaxTp());
			prod.setRealCnt(prodDTO.getRealCnt());
			prod.setProdDesc(prodDTO.getProdDesc());
			prod.setSaleStatus(prodDTO.getSaleStatus());
			prod.setDlvyTp(prodDTO.getDlvyTp());
			prod.setDlvyCost(prodDTO.getDlvyCost());
			prod.setDlvyCostRe(prodDTO.getDlvyCostRe());
			prod.setDlvyAdd(prodDTO.getDlvyAdd());

			prodRepository.save(prod);
		}
	}
	
	@Override
	public Long register2(ProdDTO prodDTO) {//등록
		S_Ditto_ProdEntity entity = dtoToEntity2(prodDTO);
		S_Ditto_ProdEntity savedEntity = prodRepository.save(entity);
		return savedEntity.getProdId();
	}
	
	
	@Override
	public void delArticle(Long prodId) {//삭제
		prodRepository.deleteById(prodId);
	}

	@Transactional
	@Override
	public ProdDTO get(Long prodId) {//특정 조회
		
		 Optional<S_Ditto_ProdEntity> optionalProd = prodRepository.findById(prodId);

		 
		 
		 if (optionalProd.isPresent() ) {
		    	S_Ditto_ProdEntity prod = optionalProd.get();

		    	List<S_Ditto_Prod_ImgEntity> imgList = prodImgRepository2.findByProductProdIdOrderByImgNo(prodId);
		    	System.out.println("상품 잘 있고 이미지도 잘 있음");
		    	
		        return entityToDto(prod, imgList);
		        
		 } else if(optionalProd.isPresent()){
		    	
		    	System.out.println("아직 사진 등록을 안해서 그래");
		    	
		    	return null;
		    	
		 } else {
		    throw new RuntimeException("상품조회 오류 발생함");
		 }
	}
	

	
	@Override
	public List<S_Ditto_ProdEntity> getList() {
	       List<S_Ditto_ProdEntity> products = prodRepository.findAll();
	        Collections.reverse(products);
		return products;
	}
	
	
	private BooleanBuilder getSearch(PageRequestDTO requestDTO) {
		String type = requestDTO.getType();
		BooleanBuilder booleanBuilder = new BooleanBuilder();

		QS_Ditto_ProdEntity qS_Ditto_ProdEntity = QS_Ditto_ProdEntity.s_Ditto_ProdEntity;

		String keyword = requestDTO.getKeyword();

		BooleanExpression exp = qS_Ditto_ProdEntity.prodId.gt(0L); // 1번 글 이상부터 검색 대상으로 시작함
		booleanBuilder.and(exp);

		if (type == null || keyword.isEmpty()) { // 검색 조건이 없음..
		    return booleanBuilder;
		}

		// 검색조건 작성
		BooleanBuilder booleanBuilder2 = new BooleanBuilder();

		if ("l".equals(type)) { // 유저 아이디으로 검색을 요청했을 경우
		    booleanBuilder2.or(qS_Ditto_ProdEntity.prodNm.contains(keyword));
		}
		
		// 위 조건 모두 통합
		booleanBuilder.and(booleanBuilder2);

		return booleanBuilder;
	}
	
	public PageResultDTO<ProdDTO, Object[]> getList2(PageRequestDTO pageRequestDto) {
		Function<Object[], ProdDTO> converFunction = 
				(entity -> entityToDto3((S_Ditto_ProdEntity)entity[0],
						(List<S_Ditto_Prod_ImgEntity>)Arrays.asList((S_Ditto_Prod_ImgEntity)entity[1]), ((Double)entity[2]),(Long)entity[3]));
				
				Pageable pageable = pageRequestDto.getPageable(Sort.by("prodId").descending());//Pageable 객체를 생성하고, 페이지 요청에 따라 정렬
				
				Page<Object[]> page = prodRepository2.getListPage(pageable);//호출하여 페이지네이션된 데이터
				
				page.getContent().forEach(row ->{//PageResultDTO는 페이지 정보와 변환 함수를 인자로 받아 페이지 결과를 생성
					System.out.println("요청된 페이지의 리턴 Data -- > " + Arrays.toString(row));
				});
				
				return new PageResultDTO<>(page, converFunction);
	}

}
