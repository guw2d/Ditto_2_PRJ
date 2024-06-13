package com.ditto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.ditto.entity.S_Ditto_CartEntity;
import com.ditto.entity.S_Ditto_MemberEntity;
import com.ditto.entity.S_Ditto_OrderEntity;

public interface S_Ditto_CartRepository extends JpaRepository<S_Ditto_CartEntity, Long>, QuerydslPredicateExecutor<S_Ditto_CartEntity>{
	 List<S_Ditto_CartEntity> findByLoginNo(S_Ditto_MemberEntity member);
	
}
