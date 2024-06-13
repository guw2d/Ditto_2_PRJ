package com.ditto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.ditto.entity.S_Ditto_OrderEntity;



public interface S_Ditto_OrderRepository extends JpaRepository<S_Ditto_OrderEntity, Long>, QuerydslPredicateExecutor<S_Ditto_OrderEntity>{
	
	// 특정 사용자의 주문 내역을 가져오는 메서드 선언
    List<S_Ditto_OrderEntity> findByMemberId(String memberId);
}
