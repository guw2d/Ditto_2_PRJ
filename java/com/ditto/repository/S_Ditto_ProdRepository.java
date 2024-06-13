package com.ditto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.ditto.entity.S_Ditto_ProdEntity;

public interface S_Ditto_ProdRepository extends JpaRepository<S_Ditto_ProdEntity, Long>, QuerydslPredicateExecutor<S_Ditto_ProdEntity>{
	
	
}
