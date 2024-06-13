package com.ditto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.ditto.entity.S_Ditto_AEntity;



public interface S_Ditto_ARepository extends JpaRepository<S_Ditto_AEntity, Long>, QuerydslPredicateExecutor<S_Ditto_AEntity>{
	
	
}
