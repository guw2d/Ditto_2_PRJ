package com.ditto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.ditto.entity.S_Ditto_QEntity;



public interface S_Ditto_QRepository extends JpaRepository<S_Ditto_QEntity, Long>, QuerydslPredicateExecutor<S_Ditto_QEntity>{
	
	
}
