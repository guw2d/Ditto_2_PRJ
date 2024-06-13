package com.ditto.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.ditto.entity.Get_ProdEntiy;

public interface Get_ProdRepository extends JpaRepository<Get_ProdEntiy, Long>, QuerydslPredicateExecutor<Get_ProdEntiy>{
	
	
	

	


}
