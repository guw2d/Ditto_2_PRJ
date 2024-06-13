package com.ditto.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "s_ditto_cart")
public class S_Ditto_CartEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long cartId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_no")
    private S_Ditto_MemberEntity loginNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prod_id")
    private S_Ditto_ProdEntity prodId;

    @Column(name = "cart_cnt")
    private int cartCount;
}
