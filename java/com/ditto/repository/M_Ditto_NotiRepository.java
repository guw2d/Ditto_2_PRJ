package com.ditto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.ditto.entity.M_Ditto_NotiEntity;



public interface M_Ditto_NotiRepository extends JpaRepository<M_Ditto_NotiEntity, Long>, QuerydslPredicateExecutor<M_Ditto_NotiEntity>{
	
	
}
