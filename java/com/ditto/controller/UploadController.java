package com.ditto.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ditto.dto.UploadResDTO;




@RestController
public class UploadController {
	//업로드파일이 저장될 폴더 패스 필드 선언
	@Value("${com.ditto.path}") //properties 의 변수 값..
	private String uploadPath;
	
	//이미지를 뿌려주는 display 요청 처리
	/*
	웹 애플리케이션에서 외부 파일(예: 이미지 파일)을 브라우저에 표시하는 방법
	서버는 클라이언트의 요청에 따라 이미지 파일을 읽어서 브라우저로 스트리밍하여 전송
	
	클라이언트는 서버에게 어떤 이미지를 보여줄 것인지에 대한 파일 정보를 전송하고, 
	서버는 이 정보를 사용하여 해당 파일을 읽어와서 브라우저에 전송
	이때 주의해야 할 점은 MIME 타입입니다. MIME 타입은 전송되는 데이터의 형식을 나타내는데, 
	바이너리 파일이므로 이를 적절히 처리해야 함
	
	Java의 nio 패키지를 사용하여 파일을 처리하고, 
	데이터를 스트림으로 전송하는 것이 일반적입니다. 
	이를 통해 클라이언트는 이미지 파일을 받아서 화면에 표시할 수 있음.
	 */
	@GetMapping("/display")
	public ResponseEntity<byte[]> getFile(@RequestParam("fileName") String fileName){
		ResponseEntity<byte[]> responseEntity  = null;
		
		//encoding 된 파라미터 정보를 decode
		try {
			String srcFileName = URLDecoder.decode(fileName);// URL 인코딩된 문자열을 디코딩하는 Java의 메서드
			
			System.out.println("요청된 파일 명 --> " + srcFileName);
			
			//해당 파일을 File 객체로 생성함..
			File file = new File(uploadPath + File.separator + srcFileName);
			
			//브라우저에게 마임타입을 전송해야합니다. 이때 header 에 정보를 실어보내는데
			//boot 에서는 HttpHeaders 라는 객체를 이용해서 key,value 의 map 형태로 add 해서 보낼수 있음
			//반드시 데이터가 넘어가기 전에 해더부터 전송..
			HttpHeaders headers = new HttpHeaders();
			//MIME 처리..
			headers.add("Content-Type", Files.probeContentType(file.toPath()));
			
			//스트림을 통해서 브라우저에 데이터 전송합니다. 이걸 간단하게 해주는 스프링부트의 API 가 있는데
			//FileCopyUtils 라는 넘입니다. 이중 byte[] 를 리턴해주는 copy.....() 메서드를 사용합니다.
			//반드시 ResponseEntity 에 담겨서 가야함.
			responseEntity = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file),HttpStatus.OK);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return responseEntity;
		
	}
	
	@PostMapping("/uploadAjax")
	public ResponseEntity<List<UploadResDTO>> uploadFile(@RequestParam("uploadFiles") MultipartFile[] multipartFiles) {
		
		//클라이언트 브라우저에 보낼 DTO List 생성합니다.
		List<UploadResDTO> resDTOList = new ArrayList<>();
		
		for(MultipartFile file : multipartFiles) {
			//브라우저에 따라서 업로드된 파일명이 path 를 포함 할때도 있고 아닐때도 있음(IE 는 포함함)
			//때문에 만약 경로정보까지 포함되어 있다면 분리해서 파일명만 추출함.
			//파일에 대한 모든 정보는 MultipartFile 이넘이 다 가지고 있음..메서드로 필요정보 get 하면됨..
			
			//여러분이 현업에서 일을 할때, 특정 확장자를 가진 파일만 올려야 되도록 보안이 설정 되있을가능성이 많습니다.
			//이때 파일이 업로드시 반드시 특정 확장자인 경우인지를 확인해야 하는 로직이 공통코드에 설정 되어있을거에요.
			//이를 예시들어, 이미지 파일만 올릴수 있도록 필터링 해볼게요.
			//위에 설명했듯이, 업로드 요청된 파일의 모든 정보는 MultiparFile 이 가지고 있음..
			//이중에 파일타입은 getContentType() 으로 얻어낼수 있음.
			
			//결과형식은 json 으로 보낼건데, 보낼 데이터는 아래와 같음
			/*
			 *  1. 업로드된 origin file Name
			 *  2. 파일의 uuid 값
			 *  3. 업로된 파일의 저장 패스
			 *  
			 *  이렇게 하는 이유는 나중에 위 정보를 이용해서 삭제까지 하려고함.
			 */
			

			String oriName = file.getOriginalFilename();//업로드된 파일의 원본 파일 이름을 얻기
			String fileName =  oriName.substring(oriName.indexOf("\\") + 1);//원본 파일 이름에서 파일 경로를 제거하여 실제 파일 이름만 추출합니다. 파일 경로는 파일 시스템에서 파일이 저장된 위치를 나타내는데, 보안 및 파일 시스템 호환성을 고려하여 파일 이름만 추출합
			
			if(file.getContentType().startsWith("image") == false) {//업로드된 파일의 MIME 타입을 가져옴 
				System.out.println("이미지 파일 아님...");//"image"로 시작하는지를 확인 이미지 파일이 아니면 파일 아님 출력
				return new ResponseEntity<>(HttpStatus.FORBIDDEN);//HTTP 상태 코드 403(Forbidden)을 반환
			}
			
			//저장할 폴더경로 얻어냅니다.
			String folderPath = mkFolder();
			
			//파일은 오리진이름과, UUID 를 이용한 변조된 이름 두개를 갖도록 합니다.
			String uuid = UUID.randomUUID().toString();
			
			//저장할 파일이름 중간에 "_" 이용해서 UUID 와 합침..
			//실제 이 이름이 저장될 이름으로 사용될 예정임.
			String saveName = uploadPath + File.separator + folderPath + File.separator + uuid + fileName;
			
			System.out.println("saveName ---> " + saveName);
			
			//파일이나 디렉터리의 경로를 나타내는 문자열을 Path 객체로 변환하여 저장
			Path savePath = Paths.get(saveName);
			
			try {
				file.transferTo(savePath);//클라이언트가 업로드한 파일을 서버의 파일 시스템에 저장하는 역할
				
				//썸네일이미지도 생성해서 같이 저장할게요. UUID 전에 s라는 소문자를 넣어서 이름을 구성하도록 할게요.
				//썸네일 이미지를 생성하는 객체 사용법..
				/*
				 * Thumbnailator 객체의 static 메서드인 create....() 을 호출해서
				 * 파라미터만 채워주면됩니다.
				 * 
				 * 기본적으로, 파일 저장 Path, 대상파일, width, heigth 등이 파라미터임..
				 */
				String thumbImgName = uploadPath + File.separator + folderPath + File.separator + "thum_" + uuid + fileName;
				
				//File 객체를 위 경로를 대상으로 생성합니다.
				File thumFile = new File(thumbImgName);
				
				//썸네일생성합니다.
				//Thumbnailator.createThumbnail(savePath.toFile(), thumFile, 100, 100);
				
				//파일이 저장이 완료됐으니, DTO 를 생성해서 리스트에 넣어주면 됨.
				resDTOList.add(new UploadResDTO(fileName, uuid, folderPath));
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}//End of for...
		
		return new ResponseEntity<>(resDTOList,HttpStatus.OK);
	}
	
	//삭제 요청 처리 매핑
	@PostMapping("/removeFile")
	public ResponseEntity<Boolean> delFile(@RequestParam("fileName") String fileName){
		//삭제는 File 객체의 delete()를 호출하면 삭제됨.
		
		String targetFile = null;
		
		try {
			targetFile = URLDecoder.decode(fileName,"UTF-8");
			
			System.out.println("삭제 요청된 파일 명 : " + targetFile);
			
			File file = new File(targetFile);
			file.delete();
			
			return new ResponseEntity<>(true, HttpStatus.OK);
			
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	//Root 하위에 생성될 날짜이름으로 생성될 폴더를 생성하는 메서드 정의
	private String mkFolder() {
		//현재 날짜정보를 얻어냅니다..폴더명으로 사용됨
		String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy"));
		
		//새로운 폴더를 생성할건데, 반드시 root 하위에 존재해야함..
		File uploadPathFolder = new File(uploadPath, str);
		if(!uploadPathFolder.exists()) {
			uploadPathFolder.mkdirs();
		}
		
		return str;
	}
}
