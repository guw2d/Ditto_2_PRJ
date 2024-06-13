package com.ditto.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "s_ditto_prod")
public class S_Ditto_ProdEntity extends BaseEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prod_id")
    private Long prodId;

    @Column(name = "prod_nm")
    private String prodNm;
    
    @Column(name = "user_id")
    private String userId;

    @Column(name = "origin_price")
    private int originPrice;

    @Column(name = "sale_price")
    private int salePrice;

    @Column(name = "margin")
    private double margin;

    @Column(name = "tax_tp")
    private String taxTp;

    @Column(name = "real_cnt")
    private int realCnt;

    @Column(name = "prod_desc")
    private String prodDesc;

    @Column(name = "sale_status")
    private String saleStatus;

    @Column(name = "dlvy_tp")
    private String dlvyTp;

    @Column(name = "dlvy_cost")
    private int dlvyCost;

    @Column(name = "dlvy_cost_re")
    private int dlvyCostRe;

    @Column(name = "dlvy_add")
    private String dlvyAdd;
    
    @Column(name = "path_url")
    private String pathUrl;
    

    @ManyToOne
    @JoinColumn(name = "ctg_cd")
    private S_Ditto_CtgEntity ctgCd;
}
