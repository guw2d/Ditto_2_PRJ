package com.ditto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.ditto.entity.S_Ditto_Prod_ImgEntity;

public interface S_Ditto_Prod_ImgRepository extends JpaRepository<S_Ditto_Prod_ImgEntity, Long>, QuerydslPredicateExecutor<S_Ditto_Prod_ImgEntity>{
	
	
}
