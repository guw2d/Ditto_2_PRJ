package com.ditto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ditto.entity.S_Ditto_Prod_ImgEntity;

public interface S_Ditto_Prod_ImgRepository2 extends JpaRepository<S_Ditto_Prod_ImgEntity, Long> {

    List<S_Ditto_Prod_ImgEntity> findByProductProdIdOrderByImgNo(Long prodId);
    

}