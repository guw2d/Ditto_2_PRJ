package com.ditto.service;

import java.util.List;

import com.ditto.dto.CartDTO;
import com.ditto.dto.OrderDTO;
import com.ditto.dto.PageRequestDTO;
import com.ditto.dto.PageResultDTO;
import com.ditto.entity.S_Ditto_CartEntity;
import com.ditto.entity.S_Ditto_MemberEntity;
import com.ditto.entity.S_Ditto_OrderEntity;
import com.ditto.entity.S_Ditto_ProdEntity;

public interface S_Ditto_OrderService {
	
	default S_Ditto_OrderEntity dtoToEntity(OrderDTO dto) {
		//DTO --> Entity 변환 메서드 정의합니다.
		S_Ditto_OrderEntity Order = S_Ditto_OrderEntity.builder()
				.orderNo(dto.getOrderNo())
				.prodId(dto.getProdId())
				.prodNm(dto.getProdNm())
				.memberId(dto.getMemberId())
				.orderQuantity(dto.getOrderQuantity())
				.orderPrice(dto.getOrderPrice())
				.orderDate(dto.getOrderDate())
				.orderStatus(dto.getOrderStatus())
				.expectYn(dto.getExpectYn())
				.ordImg(dto.getOrdImg())
				.build();
		return Order;
    }
	
	//Entity --> DTO 변환 메서드 정의
	default OrderDTO entityToDto(S_Ditto_OrderEntity dto) {
		OrderDTO orderDTO = OrderDTO.builder()
				.orderNo(dto.getOrderNo())
				.prodId(dto.getProdId())
				.prodNm(dto.getProdNm())
				.memberId(dto.getMemberId())
				.orderQuantity(dto.getOrderQuantity())
				.orderPrice(dto.getOrderPrice())
				.orderDate(dto.getOrderDate())
				.orderStatus(dto.getOrderStatus())
				.expectYn(dto.getExpectYn())
				.ordImg(dto.getOrdImg())
				.build();
		return orderDTO;
	}
	
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
	
	default S_Ditto_OrderEntity dtoToEntity2(OrderDTO dto) {
		//DTO --> Entity 변환 메서드 정의합니다.
		S_Ditto_OrderEntity Order = S_Ditto_OrderEntity.builder()
				.prodId(1L)
				.prodNm("aa")
				.memberId("user1")
				.orderQuantity(1)
				.orderPrice(3000)
				.orderStatus("N")
				.expectYn("N")
				.build();
		return Order;
    }
	
	
	
	default S_Ditto_OrderEntity dtoToEntity3(OrderDTO dto) {
		//DTO --> Entity 변환 메서드 정의합니다.
		S_Ditto_OrderEntity Order = S_Ditto_OrderEntity.builder()
				.prodId(1L)
				.orderStatus("N")
				.expectYn("N")
				.build();
		return Order;
    }
	
	
	//신규 글 등록 메서드 선언.
	Long register(OrderDTO OrderDTO);
	
	//수정
	void updateArticle(OrderDTO OrderDTO);
	
	//삭제
	void delArticle(Long OrderNo);
	
	//특정조회
	OrderDTO get(Long OrderNo);
	
	//리스트 출력
	public List<S_Ditto_OrderEntity> getList();
	
	//셀러 주문 조회 리스트 출력
	PageResultDTO<OrderDTO, S_Ditto_OrderEntity> s_getList(PageRequestDTO pageRequestDTO);
	
	// 특정 사용자의 주문 내역을 가져오는 메서드
    List<OrderDTO> getOrdersByMemberId(String memberId);

    // 주문번호로 찾음
	boolean cancelOrder(Long orderId);
    

}
