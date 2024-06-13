package com.ditto.controller.admin;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ditto.dto.CartDTO;
import com.ditto.dto.MemberDTO;
import com.ditto.service.MemberService;
import com.ditto.service.S_Ditto_CartService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@SpringBootApplication
@Controller
@RequestMapping("/admin")//localhost/ditto(context)/list(path)
@Log4j2
@RequiredArgsConstructor//클래스내부의 필드중 private final 로 선언된 inteface 의 하위 객체를 자동 주입시키는 어노테이션임.
@Secured("C")
public class prodConrtroller {
	
	 @Autowired 
	 private MemberService memberService;
	 
	private final S_Ditto_CartService cartService;

	@GetMapping("/addcart")
    public String addcart(@RequestParam("prodId") Long prodId, @RequestParam("cartCount") int cartCount , Principal principal ) {
		String loginId = principal.getName(); // 로그인된 사용자의 loginId를 가져옵니다.
		
	       
	     MemberDTO member = memberService.getMemberByLoginId(loginId); // 회원 조회
	       
	       
		   CartDTO dto = CartDTO.builder()
		    		.loginNo(member.getMemberNo())
		    		.productId(prodId)
		    		.cartCount(cartCount)
		    		.build();
					
		   cartService.register(dto);
    	 return "redirect:cart";
    }
}
