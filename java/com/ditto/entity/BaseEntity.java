package com.ditto.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;

// BaseEntity는 공통적인 부분을 정의한 추상 클래스입니다. 
// 이 클래스는 실제로 데이터베이스에 매핑되는 테이블을 가지지 않습니다.
// BaseEntity를 상속받는 하위 Entity들이 실제로 데이터베이스와 매핑되고 관리됩니다.

import lombok.Getter;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
public abstract class BaseEntity {

    @CreatedDate
    @Column(name = "i_dt", updatable = false)
    private LocalDateTime iDt;

    @LastModifiedDate
    @Column(name = "u_dt")
    private LocalDateTime uDt;

    @PrePersist
    public void onPrePersist() {
        this.iDt = LocalDateTime.now();
        this.uDt = null; // 등록 시점에서는 수정 날짜를 null로 설정
    }

    @PreUpdate
    public void onPreUpdate() {
        this.uDt = LocalDateTime.now();
    }
}