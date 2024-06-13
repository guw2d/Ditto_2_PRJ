package com.ditto.entity;

import java.time.LocalDateTime;

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

@Table(name = "s_ditto_prod_img")
public class S_Ditto_Prod_ImgEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "img_No")
    private Long imgNo;
    
	@Column(name = "img_path")
    private String path;
    
	@Column(name = "img_nm")
	private String imgName;
	
	@Column(name = "uuid")
	private String uuid;
	
	@Column(name = "img_i_dt")
    private LocalDateTime imgIDt;
    
	@Column(name = "img_u_dt")
    private LocalDateTime imgUDt;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prod_id")
    private S_Ditto_ProdEntity product;
}
