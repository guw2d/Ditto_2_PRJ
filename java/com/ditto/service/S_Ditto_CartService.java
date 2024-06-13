package com.ditto.service;

import java.util.List;

import com.ditto.dto.CartDTO;
import com.ditto.entity.S_Ditto_CartEntity;
import com.ditto.entity.S_Ditto_MemberEntity;
import com.ditto.entity.S_Ditto_ProdEntity;

public interface S_Ditto_CartService {
	
	default S_Ditto_CartEntity dtoToEntity(CartDTO dto) {
		//DTO --> Entity 변환 메서드 정의합니다.
		S_Ditto_CartEntity Cart = S_Ditto_CartEntity.builder()
				.cartId(dto.getCartId())
				.loginNo(S_Ditto_MemberEntity.builder().memberNo(dto.getLoginNo()).build())
				.prodId(S_Ditto_ProdEntity.builder().prodId(dto.getProductId()).build())
				.cartCount(dto.getCartCount())
				.build();
		return Cart;
    }
	
	//Entity --> DTO 변환 메서드 정의
	default CartDTO entityToDto(S_Ditto_CartEntity dto) {
		CartDTO cartDTO = CartDTO.builder()
				.loginNo(dto.getLoginNo().getMemberNo())
				.productId(dto.getProdId().getProdId())
				.cartCount(dto.getCartCount())
				.build();
		return cartDTO;
	}
	//신규 글 등록 메서드 선언.
	Long register(CartDTO CartDTO);
	
	//수정
	void updateArticle(CartDTO CartDTO);
	
	//삭제
	void delArticle(Long CartNo);
	
	//특정조회
	CartDTO get(Long CartNo);
	
	//리스트 출력
	public List<S_Ditto_CartEntity> getList();

	List<CartDTO> getCartByMemberId(String memberId);
}
