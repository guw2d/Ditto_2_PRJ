package com.ditto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.ditto.entity.S_Ditto_MemberEntity;

public interface MemberRepository extends JpaRepository<S_Ditto_MemberEntity, Long>, QuerydslPredicateExecutor<S_Ditto_MemberEntity>{



	


}
