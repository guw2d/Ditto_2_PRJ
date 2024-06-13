package com.ditto.dto;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder//Builder 패턴을 자동으로 생성해주는 기능
@NoArgsConstructor
@AllArgsConstructor
@Data//@Getter, `@설정@Setter, @EqualsAndHashCode, @ToString, `@NoAr@NoArgsConstructor, @AllArgsConstructor 등의 Lombok 어노테이션을 모두 자동으로 적용
public class Prod_ImgDTO {
	private Long imgNo;
    
	private String path;
	
	//private String product;
	
	private String uuid;
	
	private String imgName;
	
	private LocalDateTime imgIDt;
	
	private LocalDateTime imgUDt;
	
	//Image 가 있는 URL 경로를 리턴하는 메서드 정의
		public String imageDTOList() {
			//이 URL 정보는 여러분이 업로드한 이미지를 볼수 있도록 URL 정보를 담고 있음..
			//그런데, 이 정보 주고 받을때, 기본적인 en/decoding 을 통해서 처리하도록 함.
			
			try {
				return URLEncoder.encode(path + "/" +uuid+ imgName,"UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return null;
		}
		
		public String getThumImageURL() {
			try {
				return URLEncoder.encode(path + "/" + "thum_" + uuid + imgName,"UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return null;
		}
	
}
