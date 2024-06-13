package com.ditto.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ditto.dto.MemberDTO;
import com.ditto.dto.OrderDTO;
import com.ditto.dto.PageRequestDTO;
import com.ditto.dto.PageResultDTO;
import com.ditto.entity.QS_Ditto_MemberEntity;
import com.ditto.entity.QS_Ditto_OrderEntity;
import com.ditto.entity.S_Ditto_MemberEntity;
import com.ditto.entity.S_Ditto_OrderEntity;
import com.ditto.repository.S_Ditto_OrderRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class S_Ditto_OrderServiceImpl implements S_Ditto_OrderService {
	
	private final S_Ditto_OrderRepository orderRepository;
	
	@Override
    public boolean cancelOrder(Long orderId) {
        try {
            // 주문 번호를 이용하여 주문을 찾습니다.
            Optional<S_Ditto_OrderEntity> optionalOrder = orderRepository.findById(orderId);
            
            // 주문이 존재하면 삭제합니다.
            if (optionalOrder.isPresent()) {
                orderRepository.delete(optionalOrder.get());
                return true; // 주문 취소 성공
            } else {
                return false; // 주문이 존재하지 않음
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false; // 주문 취소 실패
        }
    }

	
	//주문내역 조회
	@Override
    public List<OrderDTO> getOrdersByMemberId(String memberId) {
		
        // 사용자 아이디를 기반으로 해당 사용자의 주문 내역을 조회하여 DTO로 변환하여 반환
        List<S_Ditto_OrderEntity> orders = orderRepository.findByMemberId(memberId);
        return orders.stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }
	
	@Override
	public Long register(OrderDTO OrderDTO) {
		S_Ditto_OrderEntity entity = dtoToEntity(OrderDTO);
		S_Ditto_OrderEntity savedEntity = orderRepository.save(entity);
		return savedEntity.getProdId();
	}


	@Override
	public void updateArticle(OrderDTO OrderDTO) {
		Optional<S_Ditto_OrderEntity> res = orderRepository.findById(OrderDTO.getOrderNo());
		if(res.isPresent()) {
			S_Ditto_OrderEntity order = res.get();
			order.setOrderNo(OrderDTO.getOrderNo());

			order.setOrderStatus(OrderDTO.getOrderStatus());


			orderRepository.save(order);
		}
		
	}

	@Override
	public void delArticle(Long OrderNo) {
		orderRepository.deleteById(OrderNo);
		
	}

	@Override
	public OrderDTO get(Long OrderNo) {
		 Optional<S_Ditto_OrderEntity> optionalOrder = orderRepository.findById(OrderNo);
		    if (optionalOrder.isPresent()) {
		    	S_Ditto_OrderEntity order = optionalOrder.get();
		        return entityToDto(order);
		    } else {
		        // 조회된 회원이 없을 경우 null이 아닌 다른 값이나 예외 처리를 해야 합니다.
		        throw new RuntimeException("해당하는 주문을 찾을 수 없습니다.");
		    }
	}

	@Override
	public List<S_Ditto_OrderEntity> getList() {
		return orderRepository.findAll();
	}
	
	@Override
	public PageResultDTO<OrderDTO, S_Ditto_OrderEntity> s_getList(PageRequestDTO pageRequestDTO) {
		Pageable pageable = pageRequestDTO.getPageable(Sort.by("orderNo").descending());
		BooleanBuilder booleanBuilder = getSearch(pageRequestDTO);//getSearch 메서드는 검색 조건을 생성하는 역할
		Page<S_Ditto_OrderEntity> result = orderRepository.findAll(booleanBuilder, pageable);
		
		Function<S_Ditto_OrderEntity, OrderDTO> fn = this::entityToDto;
		
		return new PageResultDTO<>(result, fn);
	}

	
	private BooleanBuilder getSearch(PageRequestDTO requestDTO) {
		String type = requestDTO.getType();
		BooleanBuilder booleanBuilder = new BooleanBuilder();

		QS_Ditto_OrderEntity s_Ditto_order = QS_Ditto_OrderEntity.s_Ditto_OrderEntity;

		String keyword = requestDTO.getKeyword();

		BooleanExpression exp = s_Ditto_order.orderNo.gt(0L); // 1번 글 이상부터 검색 대상으로 시작함
		booleanBuilder.and(exp);

		if (type == null || keyword.isEmpty()) { // 검색 조건이 없음..
		    return booleanBuilder;
		}

		// 검색조건 작성
		BooleanBuilder booleanBuilder2 = new BooleanBuilder();

		if ("l".equals(type)) { // 제목으로 검색을 요청했을 경우
		    booleanBuilder2.or(s_Ditto_order.memberId.contains(keyword));
		}
		if ("n".equals(type)) { // 내용으로 검색 요청시
		    booleanBuilder2.or(s_Ditto_order.prodNm.contains(keyword));
		}
		if ("e".equals(type)) { // 작성자로 검색 요청시
		    booleanBuilder2.or(s_Ditto_order.orderStatus.contains(keyword));
		}

		// 위 조건 모두 통합
		booleanBuilder.and(booleanBuilder2);

		return booleanBuilder;
	}



}
