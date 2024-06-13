package com.ditto.controller.admin;

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

import com.ditto.dto.CartDTO;
import com.ditto.dto.S_ADTO;
import com.ditto.dto.S_QDTO;
import com.ditto.entity.S_Ditto_QEntity;
import com.ditto.service.S_Ditto_AService;
import com.ditto.service.S_Ditto_CartService;
import com.ditto.service.S_Ditto_QService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@SpringBootApplication
@Controller
@RequestMapping("/admin")//localhost/ditto(context)/list(path)
@Log4j2
@RequiredArgsConstructor//클래스내부의 필드중 private final 로 선언된 inteface 의 하위 객체를 자동 주입시키는 어노테이션임.
@Secured("C")
public class QnaConrtroller {
    private final S_Ditto_QService sqService;
    private final S_Ditto_AService saService;
	//소비자-판매자 문의 게시판 리스트 보여주기
    @GetMapping("/sellerQna")
    public String sellerQna(Model model) {

    	List<S_Ditto_QEntity> res = sqService.getList();
    	model.addAttribute("result", res);
    	return "pages/admin/list/sellerQna";
    }
    
   
    //소비자-판매자 문의 작성창 접속
    @GetMapping("/inquiry")
    public String inquiry() {
    	    	    	
    	return "pages/admin/list/inquiry";
    }
    
    
    //소비자-판매자 문의 작성, 작성 완료하면 문의게시판으로 redirect
    @PostMapping("/inquiry")
    public String inquiry(S_QDTO QDTO, RedirectAttributes redirectAttributes) {
    	
    	System.out.println("요청DTO: " + QDTO);
    	Long sQnumber = sqService.register(QDTO);
    	System.out.println("등록된 문의글 번호: " + sQnumber);
    	redirectAttributes.addFlashAttribute("sQnumber", sQnumber);
    	return "redirect:/admin/sellerQna";
    }
    
    
    //소비자-판매자 특정 문의글 내용 보기
    @GetMapping("/readSQ")
    public String readSQ(@RequestParam("qnaNo") Long qnaNo, Model model) {

    	model.addAttribute("dto", sqService.get(qnaNo));   	
    	return "pages/admin/list/readSQ";
    }
    
    
    //소비자-판매자 특정 문의글 수정페이지 접속
    @GetMapping("/modiSQ")
    public String modiSQ(@RequestParam("qnaNo") Long qnaNo, Model model) {

    	model.addAttribute("dto", sqService.get(qnaNo));
    	return "pages/admin/list/modiSQ";
    }
     

    //소비자-판매자 특정 문의글 수정
    @PostMapping("/modiSQ")
    public String modiSQ(S_QDTO QDTO, RedirectAttributes redirectAttributes) {

    	sqService.updateArticle(QDTO);
    	redirectAttributes.addAttribute("qnaNo", QDTO.getQnaNo());    	
    	return "redirect:/admin/sellerQna";
    }    
    
    
    //소비자-판매자 특정 문의글 삭제
    @PostMapping("/delSQ")
    public String delSQ(@RequestParam("qnaNo") Long qnaNo, RedirectAttributes redirectAttributes) {
    	
    	sqService.delArticle(qnaNo);
    	redirectAttributes.addAttribute("qnaNo", qnaNo);
    	return "redirect:/admin/sellerQna";
    }
    
    
    //소비자-판매자 문의내용에 대한 답글등록창 접속
    @GetMapping("/regiSA")
    public String regiSA() {
    	    	    	
    	return "pages/admin/list/regiSA";
    }
      
        
   //소비자-판매자 문의내용에 대한 답글 등록, 답글작성 완료하면 문의게시판으로 redirect
    @PostMapping("/regiSA")
    public String regiSA(S_ADTO S_ADTO, RedirectAttributes redirectAttributes) {
    	    	
    	System.out.println("세이브 답글내용: " + S_ADTO);
    	System.out.println("등록된 답변글 번호: " + saService.register(S_ADTO));
    	
    	redirectAttributes.addFlashAttribute("sAnumber", saService.register(S_ADTO));
    	
    	return "redirect:/admin/sellerQna";
    	
    }
}
