package com.ditto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.ditto.entity.S_Ditto_CtgEntity;

public interface S_Ditto_CtgRepository extends JpaRepository<S_Ditto_CtgEntity, Long>, QuerydslPredicateExecutor<S_Ditto_CtgEntity>{
	
	
}
