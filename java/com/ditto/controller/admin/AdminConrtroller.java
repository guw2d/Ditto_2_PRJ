package com.ditto.controller.admin;

import java.security.Principal;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ditto.dto.M_QDTO;
import com.ditto.dto.MemberDTO;
import com.ditto.dto.OrderDTO;
import com.ditto.dto.PageRequestDTO;
import com.ditto.dto.ProdDTO;
import com.ditto.entity.S_Ditto_QEntity;
import com.ditto.service.M_Ditto_QService;
import com.ditto.service.MemberService;
import com.ditto.service.S_Ditto_CartService;
import com.ditto.service.S_Ditto_OrderService;
import com.ditto.service.S_Ditto_ProdService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@SpringBootApplication
@Controller
@RequestMapping("/admin")//localhost/ditto(context)/list(path)
@Log4j2
@RequiredArgsConstructor//클래스내부의 필드중 private final 로 선언된 inteface 의 하위 객체를 자동 주입시키는 어노테이션임.
@Secured("C")
public class AdminConrtroller {
	
	private final S_Ditto_OrderService orderService;
	private final MemberService memberService;
	
	private final S_Ditto_CartService cartService;
    
    private final S_Ditto_ProdService prodService;
    private final M_Ditto_QService mqService;
    
    @GetMapping(value = {"/", "/main"})
    public String main(){
        return "pages/admin/main/main";
    }  
    
    //메인테스트
    @GetMapping("/maintest")
    public String maintest(){
        return "pages/admin/home/maintest";
    }  
    
    @GetMapping("/admin")
	public String admin() {//viewer 를 리턴하도록 string 리턴 메서드 작성..
		log.info("admin 요청됨");
		return "redirect:/admin/main";
	}


    //장바구니 페이지
    @GetMapping("/cart")
    public String cart(Model model) {
		model.addAttribute("result", cartService.getList());
    	return "pages/admin/pord/cart";
    }   
        
    //about 페이지
    @GetMapping("/about")
    public String about() {
    	return "pages/admin/home/about";
    }    
  

    //contact
    @GetMapping("/contact")
    public String contact() {
    	return "pages/admin/home/contact";
    }   


   //셀러 단독샵 페이지
    @GetMapping("/shop")
    public String shop(Model model) {
    	
    	model.addAttribute("result", prodService.getList());
    	
    	return "pages/admin/list/shop";
    }
    
    //셀러 구분 없이 전체상품 보여주는 페이지
    @GetMapping("/pordList")
    public String pordList(Model model) {
    	
    	model.addAttribute("result", prodService.getList());

    	return "pages/admin/list/pordList";
    }
    
    //상품 상세페이지
    @GetMapping("/shopMax")
    public String shopMax(@RequestParam("prodId") Long prodId, Model model) {
    	
    	ProdDTO result = prodService.get(prodId);
    	
    	model.addAttribute("dto", result);
    	
    	return "pages/admin/pord/shop-single";
    }
    
    
    
    // 옷 종류 페이지
    // MAN
    @GetMapping("/man")
    public String man(Model model) {
    	
    	model.addAttribute("result", prodService.getList());

    	return "pages/admin/pord/man";
    }
    
    
    // women
    @GetMapping("/women")
    public String women(PageRequestDTO pageRequestDTO, Model model) {
    	
    	model.addAttribute("result", prodService.getList());
    	
    	return "pages/admin/pord/women";
    }    
    
    // children  키즈
    @GetMapping("/children")
    public String children(PageRequestDTO pageRequestDTO, Model model) {
    	
    	model.addAttribute("result", prodService.getList());
    	
    	return "pages/admin/pord/children";
    } 
    //shoes
    @GetMapping("/shoes")
    public String shoes(PageRequestDTO pageRequestDTO, Model model) {
    	
    	model.addAttribute("result", prodService.getList());
    	
    	return "pages/admin/pord/shoes";
    } 
    
    //checkout 페이지
    @GetMapping("/checkout")
    public String checkout(Model model , Principal principal) { 
    	String loginId = principal.getName(); // 로그인된 사용자의 loginId를 가져옵니다.
    	MemberDTO member = memberService.getMemberByLoginId(loginId); // 회원 조회
    	
    	
    	model.addAttribute("result", cartService.getList());
    	model.addAttribute("result2", memberService.get(member.getMemberNo()));
    	
    	return "pages/admin/pord/checkout";
    }
  

	@GetMapping("/cartdel")
    public String cartdel(@RequestParam("cartNo") Long cartNo ) {
    	cartService.delArticle(cartNo);
    	return "redirect:/admin/cart";
    }
	
    //thankyou 페이지
    @GetMapping("/thankyou")
    public String thankyou() {
    	return "pages/admin/home/thankyou";
    }  

    //찜 페이지
    @GetMapping("/heart")
    public String heart() {
    	return "pages/admin/home/heart";
    }  
    
    
 // newpord
    @GetMapping("/newpord")
    public String newpord() {
    	return "pages/admin/pord/newpord";
    }
 // MAN
    @GetMapping("/best")
    public String best() {
    	return "pages/admin/pord/best";
    }

    // 신상
    @GetMapping("/NewArrivals")
    public String NewArrivals() {
    	return "pages/admin/pord/NewArrivals";
    }

    @GetMapping("/addinfo")
    public String addinfo(@RequestParam("prodId") Long prodId, @RequestParam("cartCount") int cartCount, @RequestParam("cartNo") Long cartNo) {
   System.out.println("545"+memberService.get(cartService.get(cartNo).getLoginNo()));

      ProdDTO prodDTO = prodService.get(prodId);
      
      
       log.info("-------------------" + prodDTO);
       OrderDTO dto = OrderDTO.builder()
            .prodId(prodId)
            .prodNm(prodDTO.getProdNm())
            .memberId(memberService.get(cartService.get(cartNo).getLoginNo()).getLoginId())
            .orderQuantity(cartCount)
            .orderPrice(prodDTO.getSalePrice())
            .ordImg(prodDTO.getPathUrl())
            .orderStatus("N")
            .build();
       orderService.register(dto);
       cartService.delArticle(cartNo);
   
        return "pages/admin/home/thankyou";
    }

}
