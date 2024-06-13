package com.ditto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.ditto.entity.M_Ditto_QEntity;



public interface M_Ditto_QRepository extends JpaRepository<M_Ditto_QEntity, Long>, QuerydslPredicateExecutor<M_Ditto_QEntity>{
	
	
}
