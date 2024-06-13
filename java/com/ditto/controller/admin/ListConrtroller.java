package com.ditto.controller.admin;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ditto.dto.M_NotiDTO;
import com.ditto.dto.M_QDTO;
import com.ditto.dto.PageRequestDTO;
import com.ditto.service.M_Ditto_NotiService;
import com.ditto.service.M_Ditto_QService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@SpringBootApplication
@Controller
@RequestMapping("/admin")//localhost/ditto(context)/list(path)
@Log4j2
@RequiredArgsConstructor//클래스내부의 필드중 private final 로 선언된 inteface 의 하위 객체를 자동 주입시키는 어노테이션임.
@Secured("C")
public class ListConrtroller {
	
    private final M_Ditto_QService mqService;
    private final M_Ditto_NotiService NotiService;
    
    
    //searchList 페이지
    @GetMapping("/searchList")
    public String searchList() {
    	return "pages/admin/list/searchList";
    }
    
    //qnaList
    @GetMapping("/qnaList")
    public String qnaList(PageRequestDTO pageRequestDTO, Model model) {
    	model.addAttribute("result", mqService.m_getList(pageRequestDTO));
    	return "pages/admin/list/qnaList";
    } 
    
    //qnaReg 페이지
    @GetMapping("/qnaReg")
    public String qnaReg() {
    	return "pages/admin/home/qnaReg";
    }    
    
    //상세보기
    @GetMapping({"/qnamodi"})
    public String qnamodi(@RequestParam("mQnaNo") long mQnaNo , Model model) {
  
    	M_QDTO guestBookDTO =  mqService.get(mQnaNo); 
    	
    	 model.addAttribute("dto", guestBookDTO);
    	return "pages/admin/home/qnamodi";
    }  
	//문의 등록 요청을 했을때 처리하는 메서드입니다.
	//리다이렉트시 글 성공 여부에 대한 메세지를 파라미터로 같이 보낼게요.
	//리다이렉트에 파람을 추가 하기 위해서는 반드시 RedirectAttributes 를 메서드에 선언하고 주입받도록 합니다.
	//이 메서드가 호출되면, 사용자가 작성한 내용이 모두 DTO 에 자동 매핑되도록 합니다.
	@PostMapping("/qnaReg")
	public String qnaRegPost(M_QDTO M_QDTO, RedirectAttributes redirectAttributes) {
		log.info("신규글 등록 요청됨 DTO 정보 ----> " + M_QDTO);
		Long  mQnaNo= mqService.register(M_QDTO);
		redirectAttributes.addFlashAttribute("msg", mQnaNo);//insert 후 반환되는 entity 의 글번호를 같이보냅니다.
		return "redirect:qnaList";
	}


	@PostMapping("/qnamodi")
	public String modify(M_QDTO M_QDTO, @ModelAttribute("pageReqDTO") PageRequestDTO  pageReqDTO, RedirectAttributes redirectAttributes) {
		
		log.info("수정 메서드 실행됨 -> DTO 정보 : " + M_QDTO);
		
		mqService.updateArticle(M_QDTO);
		
		//추가 정보 보내기
		redirectAttributes.addFlashAttribute("page",pageReqDTO.getPage());
		
		return "redirect:qnaList";
	}
	
	//공지사항 리스트
    @GetMapping("/notiList")
    public String notiList(PageRequestDTO pageRequestDTO, Model model) {
    	model.addAttribute("result", NotiService.m_getList(pageRequestDTO));
    	return "pages/admin/list/notiList";
    } 
    
  
    
    //상세보기
    @GetMapping({"/notimodi"})
    public String notimodi(@RequestParam("mnotiNo") long mQnaNo , Model model) {
  
    	M_NotiDTO guestBookDTO =  NotiService.get(mQnaNo); 
    	
    	 model.addAttribute("dto", guestBookDTO);
    	return "pages/admin/home/notimodi";
    }  

    @GetMapping("/qnadel")
    public String cartdel(@RequestParam("mQnaNo") Long mQnaNo ) {
    	mqService.delArticle(mQnaNo);
    	return "redirect:qnaList";
    }

}
