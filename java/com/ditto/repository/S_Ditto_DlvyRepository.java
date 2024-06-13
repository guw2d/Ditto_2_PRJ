package com.ditto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.ditto.entity.S_Ditto_DlvyEntity;



public interface S_Ditto_DlvyRepository extends JpaRepository<S_Ditto_DlvyEntity, Long>, QuerydslPredicateExecutor<S_Ditto_DlvyEntity>{
	
	
}
