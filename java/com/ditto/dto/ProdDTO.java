package com.ditto.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder//Builder 패턴을 자동으로 생성해주는 기능
@NoArgsConstructor//클래스에 기본 생성자를 자동으로 생성
@AllArgsConstructor//클래스의 모든 필드를 초기화하는 생성자를 자동으로 생성
@Data//@Getter, `@설정@Setter, @EqualsAndHashCode, @ToString, `@NoAr@NoArgsConstructor, @AllArgsConstructor 등의 Lombok 어노테이션을 모두 자동으로 적용
public class ProdDTO {
	private Long prodId;
    private String prodNm;
    private String userId;
    private int originPrice;
    private int salePrice;
    private double margin;
    private String taxTp;
    private int realCnt;
    private String prodDesc;
    private String saleStatus;
    private LocalDateTime prodIDt;
    private LocalDateTime prodUDt;
    private String dlvyTp;
    private int dlvyCost;
    private int dlvyCostRe;
    private String dlvyAdd;
    private Long ctgCd;
    private String pathUrl;
	private double avg;//평점
	private int reviewCount;//리뷰갯수
    @Builder.Default
    private List<Prod_ImgDTO> imageDTOList = new ArrayList<>();
}
