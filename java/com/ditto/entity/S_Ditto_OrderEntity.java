package com.ditto.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity//이 클래스가 엔티티임을 표시
@Getter//필드 값을 외부에서 읽어올 수 있도록 해줌
@Setter
@Builder//Builder 패턴을 자동으로 생성해주는 기능
@AllArgsConstructor//클래스의 모든 필드를 초기화하는 생성자를 자동으로 생성
@NoArgsConstructor//클래스에 기본 생성자를 자동으로 생성
@ToString//객체의 필드 값을 문자열로 표현할 수 있슴
@EntityListeners(AuditingEntityListener.class)
@Table(name = "s_ditto_order")
public class S_Ditto_OrderEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ord_no")
	private Long orderNo;
	
	@Column(name = "prod_id")
	private Long prodId;
	
	@Column(name = "prod_nm")
	private String prodNm;
	
	@Column(name = "login_id")
	private String memberId;
	
	@Column(name = "ord_qty")
	private int orderQuantity;
	
	@Column(name = "ord_price")
	private double orderPrice;
	
	@CreatedDate
	@Column(name = "ord_i_dt")
	private LocalDateTime orderDate;
	
	@Column(name = "ord_status")
	private String orderStatus;
	

	@Column(name = "ord_img")
	private String ordImg;
	
	@Column(name = "expect_yn")
	private String expectYn;
}
