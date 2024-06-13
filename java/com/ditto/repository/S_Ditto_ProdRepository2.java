package com.ditto.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ditto.entity.S_Ditto_ProdEntity;

public interface S_Ditto_ProdRepository2 extends JpaRepository<S_Ditto_ProdEntity, Long>{
	
	 List<S_Ditto_ProdEntity> findByProdId(Long prodId);
	 

	 @Query("SELECT p, pi, AVG(COALESCE(r.grade, 0)), COUNT(DISTINCT r) " +
	           "FROM S_Ditto_ProdEntity p " +
	           "LEFT JOIN S_Ditto_Prod_ImgEntity pi ON pi.product = p " +
	           "LEFT JOIN Review r ON r.prod = p " +
	           "GROUP BY p, pi ORDER BY p.id DESC")
	    Page<Object[]> getListPage(Pageable pageable);

}