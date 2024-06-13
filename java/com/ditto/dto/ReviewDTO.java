package com.ditto.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDTO implements Serializable{
	private Long reviewNum;
	
	//read.html 에 parameter 로 오기때문에
	//매핑만하면됨
	private Long mno;
	
	//member id
	private Long mid;
	
	private String nickName;
	
	private String email;
	
	private int grade;
	
	private String text;
	
	private LocalDateTime regDate,modDate;
}
