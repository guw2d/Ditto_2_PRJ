package com.ditto.controller.seller;

import java.util.List;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ditto.dto.GetProdDTO;
import com.ditto.dto.PageRequestDTO;
import com.ditto.dto.ProdDTO;
import com.ditto.entity.S_Ditto_ProdEntity;
import com.ditto.service.Get_ProdService;
import com.ditto.service.S_Ditto_CtgService;
import com.ditto.service.S_Ditto_ProdService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;


@SpringBootApplication
@Controller
@RequestMapping("/seller")//localhost/ditto(context)/list(path)
@Log4j2
@RequiredArgsConstructor//클래스내부의 필드중 private final 로 선언된 inteface 의 하위 객체를 자동 주입시키는 어노테이션임.
@Secured("S")
public class PordController {
		
	private final S_Ditto_ProdService prodService;
	private final Get_ProdService getprodService;
    private final S_Ditto_CtgService ctgService;
    
    @GetMapping("/pordReg")
    public String pordReg() {
    	return "pages/seller/prod/pordReg";
    }
    
    @PostMapping("/prodReg")
    public String pordRegPost(ProdDTO prodDTO , RedirectAttributes redirectAttributes) {
    	
    	//System.out.println("ddd"+prodDTO);
    	Long prodid = prodService.register(prodDTO);
    	
    	if(prodid < 0) {
			System.out.println("상품 등록 예외 발생함...");
			return null;
		}
    	redirectAttributes.addFlashAttribute("msg","상품이 잘 등록되었음..등록번호 : " + prodid);
    	return "redirect:pordList";
    }
    
    @PostMapping("/prodRegtest")
    public String prodRegtest( ) {
    	

    	
    	
       	ProdDTO dto = ProdDTO.builder()
                .prodNm("울 디태처블 더플 코트")
               .originPrice(100000)
               .salePrice(180000)
               .margin(0.0)
               .taxTp("1")
               .realCnt(123)
               .prodDesc("<img src='../../../ditto/2024/남성아우터1_상세.jpg'/>")
               .saleStatus("판매중")
               .dlvyTp("1")
               .dlvyCost(2500)
               .dlvyCostRe(2500)
               .dlvyAdd("<img src='../../../ditto/2024/남성아우터1_상세.jpg'/>")
               .ctgCd(1L)
               .pathUrl("남성아우터1.jpg")
                .build();
	    System.out.println(prodService.register2(dto));

    	return "redirect:pordList";
    }
    @GetMapping("/pordList")
    public String pordList(PageRequestDTO pageRequestDTO,Model model) {
    	
    	log.info("41545"+prodService.m_getList(pageRequestDTO));
    	model.addAttribute("result", prodService.m_getList(pageRequestDTO));
    	    	
 
    	return "pages/seller/prod/pordList";
    }
    
    
    
    @GetMapping("/pordModi")
    public String pordModi(@RequestParam("prodId") Long prodId,
    					   Model model) {
    	
    	ProdDTO dto = prodService.get(prodId);
    	
    	model.addAttribute("result", dto);
    	
    	return "pages/seller/prod/pordModi";
    }
    
    
    @PostMapping("/pordModi")
    public String pordModiPost(ProdDTO prodDTO) {
    	
    	
    	prodService.updateArticle(prodDTO);
    	

    	return "pages/seller/prod/pordModi";
    }
    

    @GetMapping("/pordGet")
    public String pordGet(PageRequestDTO pageRequestDTO, Model model) {
    	System.out.println("요청된 목록 페이지 --> " + pageRequestDTO);
    	model.addAttribute("result", getprodService.m_getList(pageRequestDTO));
    	return "pages/seller/prod/pordGet";
    }
    
    
    @GetMapping("/prodpost")
    public String prodpost(@RequestParam("prodId") Long prodId) {

    	GetProdDTO get = getprodService.get(prodId);
    	
    	
       	ProdDTO dto = ProdDTO.builder()
                .prodNm("아플리케 크롭 스웨트 셔츠 - 오트밀")
               .originPrice(30000)
               .salePrice(40000)
               .margin(0.0)
               .taxTp("1")
               .realCnt(2970)
               .prodDesc("<img src='../../../ditto/2024/여성탑3_상세.jpg'/>")
               .saleStatus("판매중")
               .dlvyTp("1")
               .dlvyCost(2500)
               .dlvyCostRe(2500)
               .dlvyAdd("<img src='../../../ditto/2024/여성탑3_상세.jpg'/>")
               .ctgCd(3L)
               .pathUrl("여성탑3.jpg")
                .build();
	    System.out.println(prodService.register2(dto));
    	System.out.println("dfds"+get);


    	
    	return "redirect:pordGet";
    }
    // 옷 종류 페이지
    
    // MAN
    @GetMapping("/man")
    public String man(Model model) {
    	
    	model.addAttribute("result", prodService.getList());

    	return "pages/seller/prod/man";
    }
    
    // women
    @GetMapping("/woman")
    public String women(Model model) {
    	
    	model.addAttribute("result", prodService.getList());
    	
    	return "pages/seller/prod/woman";
    }    
    
    // children  키즈
    @GetMapping("/children")
    public String children(Model model) {
    	
    	model.addAttribute("result", prodService.getList());
    	
    	return "pages/seller/prod/children";
    } 
    
    
    //상품 삭제
    @GetMapping("/proddel")
    public String cartdel(@RequestParam("prodId") Long prodId ) {
    	prodService.delArticle(prodId);
    	return "redirect:pordList";
    }
    
    
    
    
    
    
    
}
