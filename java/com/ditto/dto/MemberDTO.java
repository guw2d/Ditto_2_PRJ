package com.ditto.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Builder//Builder 패턴을 자동으로 생성해주는 기능
@Data // 롬복 애노테이션으로 Getter, Setter, equals, hashCode, toString 자동 생성
@NoArgsConstructor // 롬복 애노테이션으로 기본 생성자 자동 생성
@AllArgsConstructor // 롬복 애노테이션으로 모든 필드를 사용하는 생성자 자동 생성
@Getter
@Setter
public class MemberDTO {
	private Long memberNo;
    private String loginId;
    private String loginPw;
    private String memberNm;
    private String memberEmail;
    private String memberAddr;
    private String memberZipcd;
    private String memberTel;
    private LocalDateTime IDt,UDt;
    private String memberDt;
}
