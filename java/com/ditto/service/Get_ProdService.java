package com.ditto.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.ditto.dto.GetProdDTO;
import com.ditto.dto.MemberDTO;
import com.ditto.dto.PageRequestDTO;
import com.ditto.dto.PageResultDTO;
import com.ditto.dto.ProdDTO;
import com.ditto.entity.Get_ProdEntiy;
import com.ditto.entity.S_Ditto_MemberEntity;
import com.ditto.entity.S_Ditto_Prod_ImgEntity;

public interface Get_ProdService {
	
	default Get_ProdEntiy dtoToEntity(GetProdDTO dto) {
		//DTO --> Entity 변환 메서드 정의합니다.
		Get_ProdEntiy get = Get_ProdEntiy.builder()
	    		.prodId(dto.getProdId())
				.prodNm(dto.getProdNm())
				.wholesaleNm(dto.getWholesaleNm())
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
				.ctgCd(dto.getCtgCd())
				.uuid(dto.getUuid())
				.imgName(dto.getImgName())
				.build();
		return get;
    }
	
	//Entity --> DTO 변환 메서드 정의
	default GetProdDTO entityToDto(Get_ProdEntiy dto) {
		GetProdDTO get = GetProdDTO.builder()
	    		.prodId(dto.getProdId())
				.prodNm(dto.getProdNm())
				.wholesaleNm(dto.getWholesaleNm())
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
				.ctgCd(dto.getCtgCd())
				.uuid(dto.getUuid())
				.imgName(dto.getImgName())
				.build();
		return get;
	}
	
	public List<Get_ProdEntiy> getList();
	//신규 글 등록 메서드 선언.
	Long register(GetProdDTO prodDTO);
	
	//리스트 항목 리턴하는 메서드 선언 합니다.
	Page<Get_ProdEntiy> findAllProducts(int pageNumber, int pageSize);
	
	//상품가져오기 리스트 출력
	PageResultDTO<GetProdDTO, Get_ProdEntiy> m_getList(PageRequestDTO pageRequestDTO);

	GetProdDTO get(Long prodId);
}
