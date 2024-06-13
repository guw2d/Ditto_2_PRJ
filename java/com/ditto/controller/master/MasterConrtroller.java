package com.ditto.controller.master;

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
import com.ditto.service.MemberService;
import com.ditto.service.S_Ditto_NotiService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/master")//localhost/ditto(context)/list(path)
@Log4j2
@RequiredArgsConstructor//클래스내부의 필드중 private final 로 선언된 inteface 의 하위 객체를 자동 주입시키는 어노테이션임.
@Secured("M")
public class MasterConrtroller {
	private final M_Ditto_QService mqService;
	private final MemberService memberService;
	private final M_Ditto_NotiService NotiService;
	
    @GetMapping(value = {"/", "/main"})
    public String main(){
        return "pages/master/main/main";
    }  
    
    //메인테스트
    @GetMapping("/mastert")
    public String maintest(){
    	return "master/master";
    }  
    @GetMapping("/master")
	public String admin() {//viewer 를 리턴하도록 string 리턴 메서드 작성..
		log.info("seller 요청됨");
		return "redirect:/master/main";
	}
    
    @GetMapping("/memberList")
    public String memberList(PageRequestDTO pageRequestDTO, Model model) {
    	System.out.println("요청된 목록 페이지 --> " + pageRequestDTO);
    	model.addAttribute("result", memberService.m_getList(pageRequestDTO));
    	return "pages/master/member/memberList";
    }
    
  //qnaList
    @GetMapping("/qnaList")
    public String qnaList(PageRequestDTO pageRequestDTO, Model model) {
    	model.addAttribute("result", mqService.m_getList(pageRequestDTO));
    	return "pages/master/qna/qnaList";
    } 
    
    //상세보기
    @GetMapping({"/qnamodi"})
    public String qnamodi(@RequestParam("mQnaNo") long mQnaNo , Model model) {
  
    	M_QDTO guestBookDTO =  mqService.get(mQnaNo); 
    	
    	 model.addAttribute("dto", guestBookDTO);
    	return "pages/master/qna/qnamodi";
    } 
    
    @PostMapping("/qnamodi")
    public String qnamodi2(M_QDTO M_QDTO, @ModelAttribute("pageReqDTO") PageRequestDTO  pageReqDTO, RedirectAttributes redirectAttributes){
		mqService.updateArticle(M_QDTO);
		System.out.println(M_QDTO);
		//추가 정보 보내기
		redirectAttributes.addFlashAttribute("page",pageReqDTO.getPage());
    	return "redirect:qnaList";
    }
    
  //게시판 등록 페이지
    @GetMapping("/notiReg")
    public String notiReg() {
    	return "pages/master/service/notiReg";
    }
    @GetMapping({"/notimodi"})
    public String notimodi(@RequestParam("mQnaNo") long mQnaNo , Model model) {
  
    	//M_QDTO guestBookDTO =  NotiService.get(mQnaNo); 
    	
    	// model.addAttribute("dto", guestBookDTO);
    	return "pages/master/service/notimodi";
    } 
    
	//공지사항 리스트
    @GetMapping("/notiList")
    public String notiList(PageRequestDTO pageRequestDTO, Model model) {
    	model.addAttribute("result", NotiService.m_getList(pageRequestDTO));
    	return "pages/master/service/notiList";
    } 
    
    
    @PostMapping("/notiReg")
	public String qnaRegPost(M_NotiDTO M_NotiDTO, RedirectAttributes redirectAttributes) {
		Long  mNo= NotiService.register(M_NotiDTO);
		redirectAttributes.addFlashAttribute("msg", mNo);//insert 후 반환되는 entity 의 글번호를 같이보냅니다.
		return "redirect:notiList";
	}
    
	@PostMapping("/notimodify")
	public String notimodify(M_NotiDTO M_NotiDTO, @ModelAttribute("pageReqDTO") PageRequestDTO  pageReqDTO, RedirectAttributes redirectAttributes) {
		
		
		NotiService.updateArticle(M_NotiDTO);
		
		//추가 정보 보내기
		redirectAttributes.addFlashAttribute("page",pageReqDTO.getPage());
		
		return "redirect:notiList";
	}
    
    
    
}
