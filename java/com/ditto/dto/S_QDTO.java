package com.ditto.dto;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder//Builder 패턴을 자동으로 생성해주는 기능
@NoArgsConstructor//클래스에 기본 생성자를 자동으로 생성
@AllArgsConstructor//클래스의 모든 필드를 초기화하는 생성자를 자동으로 생성
@Data//@Getter, `@설정@Setter, @EqualsAndHashCode, @ToString, `@NoAr@NoArgsConstructor, @AllArgsConstructor 등의 Lombok 어노테이션을 모두 자동으로 적용
public class S_QDTO {
	private Long qnaNo;
	private Long productId;
	private String productName;
	private String loginId;
	private LocalDateTime inquiryDate;
	private String inquiryTitle;
	private String inquiryContent;
	private Long orderNo;
	private String inquiryStatus;
}
