package com.ditto.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ditto.entity.S_Ditto_MemberEntity;

@Repository
public interface MemberRepository2 extends JpaRepository<S_Ditto_MemberEntity, Long>{

   
   Optional<S_Ditto_MemberEntity> findByLoginId(String loginId);
   
   @Query("SELECT m FROM S_Ditto_MemberEntity m WHERE m.loginId = :loginId")
   S_Ditto_MemberEntity findByUserId(@Param("loginId") String loginId);
   
   
   Optional<S_Ditto_MemberEntity> findByMemberNm(String memberNm);
   
   // 이메일로 회원 아이디를 조회하는 메서드
    Optional<S_Ditto_MemberEntity> findByMemberEmail(String memberEmail);
}