package com.ditto.controller.seller;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ditto.dto.OrderDTO;
import com.ditto.dto.PageRequestDTO;
import com.ditto.entity.S_Ditto_QEntity;
import com.ditto.service.S_Ditto_OrderService;
import com.ditto.service.S_Ditto_QService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@SpringBootApplication
@Controller
@RequestMapping("/seller")//localhost/ditto(context)/list(path)
@Log4j2
@RequiredArgsConstructor//클래스내부의 필드중 private final 로 선언된 inteface 의 하위 객체를 자동 주입시키는 어노테이션임.
@Secured("S")
public class SellerConrtroller {
	
	private final S_Ditto_QService sqService;
	private final  S_Ditto_OrderService orderService;
    @GetMapping(value = {"/", "/main"})
    public String main(Model model){
    	LocalDateTime currentTime = LocalDateTime.now();
    	
        // dto.inquiryDate 값이라 가정
        LocalDateTime inquiryDate = LocalDateTime.of(2024, 6, 13, 12, 0); // 예시 값
        
        // 시간 차이 계산 (분 단위)
        long minutesDifference = ChronoUnit.MINUTES.between(inquiryDate, currentTime);
        
        // Thymeleaf에 전달
        model.addAttribute("minutesDifference", minutesDifference);
        
        
    	List<S_Ditto_QEntity> res = sqService.getList();
    	model.addAttribute("resultq", res);
        return "pages/seller/main/main";
    }  
    
    @GetMapping("/seller")
	public String admin() {//viewer 를 리턴하도록 string 리턴 메서드 작성..
		log.info("seller 요청됨");
		return "redirect:/seller/main";
	}
    
    //design
    @GetMapping("/design")
    public String design() {
    	return "pages/seller/home/design";
    }
    //liveSet
    @GetMapping("/liveSet")
    public String liveSet() {
    	return "pages/seller/home/liveSet";
    }
    //liveStd
    @GetMapping("/liveStd")
    public String liveStd() {
    	return "pages/seller/home/liveStd";
    }
    //promotions
    @GetMapping("/promotions")
    public String promotions() {
    	return "pages/seller/home/promotions";
    }
    //service
    @GetMapping("/suddio")
    public String suddio() {
    	return "pages/seller/home/suddio";
    }
    //videoUp
    @GetMapping("/videoUp")
    public String videoUp() {
    	return "pages/seller/home/videoUp";
    }
    
    //blvyProg
    @GetMapping("/service")
    public String service() {
    	return "pages/seller/home/service";
    }
    //blvyProg
    @GetMapping("/subscription")
    public String subscription() {
    	return "pages/seller/home/subscription";
    }
    
    //주문
    //blvyComp
    @GetMapping("/blvyComp")
    public String blvyComp() {
    	return "pages/seller/order/blvyComp";
    }

    //blvyProg
    @GetMapping("/blvyProg")
    public String blvyProg() {
    	return "pages/seller/order/blvyProg";
    }
    
    //blvyReady
    @GetMapping("/blvyReady")
    public String blvyReady() {
    	return "pages/seller/order/blvyReady";
    }

    //blvySend
    @GetMapping("/blvySend")
    public String blvySend() {
    	return "pages/seller/order/blvySend";
    }

    //cal
    @GetMapping("/cal")
    public String cal() {
    	return "pages/seller/order/cal";
    }

    //claims
    @GetMapping("/claims")
    public String claims() {
    	return "pages/seller/order/claims";
    }

    //deposit
    @GetMapping("/deposit")
    public String deposit() {
    	return "pages/seller/order/deposit";
    }

    //orderList
    @GetMapping("/orderList")
    public String orderList(PageRequestDTO pageRequestDTO, Model model) {
    	System.out.println("요청된 목록 페이지 --> " + pageRequestDTO);
        model.addAttribute("result", orderService.s_getList(pageRequestDTO));
        return "pages/seller/order/orderList";

    	
    }

    //order
    @GetMapping("/order")
    public String order() {
    	return "pages/seller/order/order";
    }

    
    //상품

    @GetMapping("/pordExcel")
    public String pordExcel() {
    	return "pages/seller/prod/pordExcel";
    }

    @GetMapping("/pordMan")
    public String pordMan() {
    	return "pages/seller/prod/pordMan";
    }
    @GetMapping("/pordRemoved")
    public String pordRemoved() {
    	return "pages/seller/prod/pordRemoved";
    }
    @GetMapping("/pordStock")
    public String pordStock() {
    	return "pages/seller/prod/pordStock";
    }

    //qna
    @GetMapping("/noti")
    public String noti() {
    	return "pages/seller/qna/noti";
    }
    @GetMapping("/qnaList")
    public String qnaList() {
    	return "pages/seller/qna/qnaList";
    }
    
    
    @GetMapping("/salesList")
    public String salesList() {
    	return "pages/seller/qna/salesList";
    }
    

    //send
    @GetMapping("/send")
    public String send( PageRequestDTO pageRequestDTO, Model model) {
  
        	System.out.println("요청된 목록 페이지 --> " + pageRequestDTO);
            model.addAttribute("result", orderService.s_getList(pageRequestDTO));
  
    	return "pages/seller/order/send";
    }
    
    
	@GetMapping("/orderLis")
    public String send(@RequestParam("orderNo") Long orderNo) {
 
		OrderDTO dto = OrderDTO.builder()
				.orderNo(orderNo)
				.orderStatus("Y")			
	            .build();
		
		
		orderService.updateArticle(dto);;
		return "redirect:orderList";

    }

}
