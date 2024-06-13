package com.ditto.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.ditto.dto.GetProdDTO;
import com.ditto.dto.PageRequestDTO;
import com.ditto.dto.PageResultDTO;
import com.ditto.dto.ProdDTO;
import com.ditto.dto.Prod_ImgDTO;
import com.ditto.entity.S_Ditto_ProdEntity;
import com.ditto.entity.S_Ditto_Prod_ImgEntity;

public interface S_Ditto_ProdService {
	
	
	/* DTO --> Entity 변환 메서드 정의합니다. */
	default Map<String, Object> dtoToEntity(ProdDTO dto) {
		
		//0. HashMap 객체 생성
		Map<String, Object> entityMap = new HashMap<>();

		
		//1-1. prod Entity
	    S_Ditto_ProdEntity prod = S_Ditto_ProdEntity.builder()
	    		.prodId(dto.getProdId())
				.prodNm(dto.getProdNm())
				.originPrice(dto.getOriginPrice())
				.salePrice(dto.getSalePrice())
				.margin(dto.getMargin())
				.taxTp(dto.getTaxTp())
				.realCnt(dto.getRealCnt())
				.prodDesc(dto.getProdDesc())
				.saleStatus(dto.getSaleStatus())
				.dlvyTp(dto.getDlvyTp())
				.dlvyCost(dto.getDlvyCost())
				.dlvyCostRe(dto.getDlvyCostRe())
				.dlvyAdd(dto.getDlvyAdd())
				.pathUrl(dto.getPathUrl())
				.userId(dto.getUserId())
	            .build();
	    
	    //1-2. HashMap 에 prod 객체를 key 로 할당
	    entityMap.put("product", prod);
	    
	    
	    //2-1. imageList
	    List<Prod_ImgDTO> imgDTOList = dto.getImageDTOList();
	    	    
	    //2-2. imgDTO 처리
	    if(imgDTOList != null && imgDTOList.size() > 0) {
	    	List<S_Ditto_Prod_ImgEntity> prodImg = imgDTOList.stream()
	    				.map(prodImgDTO -> {
	    						
	    					S_Ditto_Prod_ImgEntity img 
	    						= S_Ditto_Prod_ImgEntity.builder()
	    													.imgNo(prodImgDTO.getImgNo())
	    													.path(prodImgDTO.getPath())
	    													.uuid(prodImgDTO.getUuid())
	    													.imgName(prodImgDTO.getImgName())
	    													.imgIDt(prodImgDTO.getImgIDt())
	    													.imgUDt(prodImgDTO.getImgUDt())
	    												.build();
	    					return img;		
	    				}).collect(Collectors.toList());
	    	
	    	entityMap.put("imgList", prodImg);
	    	
	    }

	    return entityMap;
	}
	
	

	//Entity --> DTO 변환 메서드 정의
	default ProdDTO entityToDto(S_Ditto_ProdEntity dto, List<S_Ditto_Prod_ImgEntity> prodImg) {

		//1. prod
		ProdDTO prodDTO = ProdDTO.builder()
	    		.prodId(dto.getProdId())
				.prodNm(dto.getProdNm())
				.originPrice(dto.getOriginPrice())
				.salePrice(dto.getSalePrice())
				.margin(dto.getMargin())
				.taxTp(dto.getTaxTp())
				.realCnt(dto.getRealCnt())
				.prodDesc(dto.getProdDesc())
				.saleStatus(dto.getSaleStatus())
				.dlvyTp(dto.getDlvyTp())
				.dlvyCost(dto.getDlvyCost())
				.dlvyCostRe(dto.getDlvyCostRe())
				.dlvyAdd(dto.getDlvyAdd())
				.pathUrl(dto.getPathUrl())
				.userId(dto.getUserId())
	            .build();
		//2. img
		List<Prod_ImgDTO> imgDTO = prodImg.stream()
					.map(imgs -> {
						Prod_ImgDTO img = Prod_ImgDTO.builder()
													.imgNo(imgs.getImgNo())
													.path(imgs.getPath())
													.uuid(imgs.getUuid())
													.imgName(imgs.getImgName())
													.imgIDt(imgs.getImgIDt())
													.imgUDt(imgs.getImgUDt())
													.build();
						return img;
					}).collect(Collectors.toList());
		
		System.out.println("ImgList ---> " + imgDTO);
		prodDTO.setImageDTOList(imgDTO);
	    return prodDTO;
	}
	default S_Ditto_ProdEntity dtoToEntity2(ProdDTO dto) {
		S_Ditto_ProdEntity prod = S_Ditto_ProdEntity.builder()
				.prodId(dto.getProdId())
				.prodNm(dto.getProdNm())
				.originPrice(dto.getOriginPrice())
				.salePrice(dto.getSalePrice())
				.margin(dto.getMargin())
				.taxTp(dto.getTaxTp())
				.realCnt(dto.getRealCnt())
				.prodDesc(dto.getProdDesc())
				.saleStatus(dto.getSaleStatus())
				.dlvyTp(dto.getDlvyTp())
				.dlvyCost(dto.getDlvyCost())
				.dlvyCostRe(dto.getDlvyCostRe())
				.dlvyAdd(dto.getDlvyAdd())
				.pathUrl(dto.getPathUrl())
				.userId(dto.getUserId())
				.build();
		 return prod;

	}
	//Entity --> DTO 변환 메서드 정의
	default ProdDTO entityToDto2(S_Ditto_ProdEntity dto) {
		//1. prod
		ProdDTO prodDTO = ProdDTO.builder()
	    		.prodId(dto.getProdId())
				.prodNm(dto.getProdNm())
				.originPrice(dto.getOriginPrice())
				.salePrice(dto.getSalePrice())
				.margin(dto.getMargin())
				.taxTp(dto.getTaxTp())
				.realCnt(dto.getRealCnt())
				.prodDesc(dto.getProdDesc())
				.saleStatus(dto.getSaleStatus())
				.dlvyTp(dto.getDlvyTp())
				.dlvyCost(dto.getDlvyCost())
				.dlvyCostRe(dto.getDlvyCostRe())
				.dlvyAdd(dto.getDlvyAdd())
				.pathUrl(dto.getPathUrl())
				.userId(dto.getUserId())
	            .build();
		
	    return prodDTO;
	}
	
	//Entity --> DTO 변환 메서드 정의
		default ProdDTO entityToDto3(S_Ditto_ProdEntity dto, List<S_Ditto_Prod_ImgEntity> prodImg, Double avg, Long reviewCount) {

			//1. prod
			ProdDTO prodDTO = ProdDTO.builder()
		    		.prodId(dto.getProdId())
					.prodNm(dto.getProdNm())
					.originPrice(dto.getOriginPrice())
					.salePrice(dto.getSalePrice())
					.margin(dto.getMargin())
					.taxTp(dto.getTaxTp())
					.realCnt(dto.getRealCnt())
					.prodDesc(dto.getProdDesc())
					.saleStatus(dto.getSaleStatus())
					.dlvyTp(dto.getDlvyTp())
					.dlvyCost(dto.getDlvyCost())
					.dlvyCostRe(dto.getDlvyCostRe())
					.dlvyAdd(dto.getDlvyAdd())
					.pathUrl(dto.getPathUrl())
					.userId(dto.getUserId())
		            .build();
			//2. img
			List<Prod_ImgDTO> imgDTO = prodImg.stream()
						.map(imgs -> {
							Prod_ImgDTO img = Prod_ImgDTO.builder()
														.imgNo(imgs.getImgNo())
														.path(imgs.getPath())
														.uuid(imgs.getUuid())
														.imgName(imgs.getImgName())
														.imgIDt(imgs.getImgIDt())
														.imgUDt(imgs.getImgUDt())
														.build();
							return img;
						}).collect(Collectors.toList());
			
			System.out.println("ImgList ---> " + imgDTO);
			prodDTO.setImageDTOList(imgDTO);
			prodDTO.setAvg(avg);
			prodDTO.setReviewCount(reviewCount.intValue());
		    return prodDTO;
		}
	//신규 글 등록 메서드 선언.
	Long register(ProdDTO prodDTO);

	//수정
	void updateArticle(ProdDTO prodDTO);

	//삭제
	void delArticle(Long prodId);

	//특정조회
	ProdDTO get(Long prodId);

	//리스트 출력
	public List<S_Ditto_ProdEntity> getList();



	PageResultDTO<ProdDTO, S_Ditto_ProdEntity> m_getList(PageRequestDTO pageRequestDTO);



	Long register2(ProdDTO prodDTO);
	PageResultDTO<ProdDTO, Object[]> getList2(PageRequestDTO pageRequestDto);


	

}
