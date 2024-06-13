package com.ditto.controller.admin;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ditto.Details.MemberDetailsService;
import com.ditto.dto.MemberDTO;
import com.ditto.dto.OrderDTO;
import com.ditto.entity.S_Ditto_MemberEntity;
import com.ditto.repository.MemberRepository2;
import com.ditto.service.MemberService;
import com.ditto.service.S_Ditto_OrderService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@SpringBootApplication
@Controller
@RequestMapping("/admin")//localhost/ditto(context)/list(path)
@Log4j2
@RequiredArgsConstructor//클래스내부의 필드중 private final 로 선언된 inteface 의 하위 객체를 자동 주입시키는 어노테이션임.
@Secured("C") // 구매자 권한인 "C" 입니다. 구매자,셀러 모두 접근 가능함.
public class LoginConrtroller {
	   @Autowired 
	   private MemberService memberService;
	   
	   @Autowired
	   private MemberDetailsService memberDetailsService;

	   @Autowired
	   private BCryptPasswordEncoder passwordEncoder;
	   
	   @Autowired
	   private MemberRepository2 memberRepository;
	   
	   @Autowired
	    private S_Ditto_OrderService orderService;
	   
	   //주문취소
	   @PostMapping("/orderOut")
	   public String cancelOrder(@RequestParam("orderId") Long orderId, RedirectAttributes redirectAttributes) {
	       orderService.delArticle(orderId);
	       
	       // 주문 취소 후 알림을 추가합니다.
	       redirectAttributes.addFlashAttribute("orderAlert", true);
	       
	       // 주문 취소 후 마이페이지로 리다이렉트
	       return "redirect:/admin/myPage"; 
	   }

	   //마이페이지 
	   @GetMapping("/myPage")
	   public String myPage(Model model, Principal principal) {
	      System.out.println(model+"dfddsf"+principal);
	       String loginId = principal.getName(); // 로그인된 사용자의 loginId를 가져옵니다.
	       
	       MemberDTO member = memberService.getMemberByLoginId(loginId); // 회원 조회
	       List<OrderDTO> orderList = orderService.getOrdersByMemberId(loginId); // 주문 내역 조회
	       
	       model.addAttribute("member", member);
	       model.addAttribute("orderList", orderList);
	       
	       System.out.println("마이페이지 요청 됨");
	       return "pages/admin/home/myPage";
	   } 
	    
	 
	   //탈퇴 페이지
	   @GetMapping("/secession")
	   public String secession() { 
		   return "pages/admin/login/secession";
	   } 
	   
	   // 탈퇴 처리 메서드
	   @PostMapping("/withdraw")
	   public String withdraw(@RequestParam("loginId") String loginId, @RequestParam("loginPw") String loginPw, Model model) {
		   
	       // 아이디와 비밀번호를 사용하여 회원을 조회합니다.
	       Optional<S_Ditto_MemberEntity> optionalMember = memberService.findMemberByLoginId(loginId);
	       
	       if (optionalMember.isPresent()) {
	    	   
	           // 회원이 존재하는 경우
	           S_Ditto_MemberEntity member = optionalMember.get();
	           
	           // 입력한 비밀번호와 회원의 비밀번호가 일치하는지 확인합니다.
	           if (passwordEncoder.matches(loginPw, member.getLoginPw())) {
	        	   
	               // 비밀번호가 일치하는 경우, 회원을 삭제합니다.
	               memberService.deleteMember(loginId, loginPw);
	               return "redirect:/admin/logout"; // 탈퇴 Ok 페이지로 리다이렉트
	           } else {
	               // 비밀번호가 일치하지 않는 경우
	               model.addAttribute("withdrawAlert", true); // 알럿
	               return "pages/admin/login/secession"; // 탈퇴 페이지로 리다이렉트
	           }
	       } else {
	           // 회원이 존재하지 않는 경우
	           model.addAttribute("withdrawAlert", true); // 알럿
	           return "pages/admin/login/secession"; // 탈퇴 페이지로 리다이렉트
	       }
	   }
	   
	   // 아아디 찾은 페이지
	   @PostMapping("/findLoginId")
	    public String findId(@RequestParam("email") String email, Model model) {
	      
	        Optional<S_Ditto_MemberEntity> memberOptional = memberService.findMemberIdByEmail(email);
	        
	        if (memberOptional.isPresent()) {
	            S_Ditto_MemberEntity member = memberOptional.get();
	            model.addAttribute("email", email);
	            model.addAttribute("userId", member.getLoginId());
	            return "pages/admin/login/findLoginId";
	        } else {
	           model.addAttribute("findAlert", true);

	            return "pages/admin/login/find";
	        }
	    }

	   // 비번 재설정, 패스워드 입력 받고 얘가 변경 처리함
	   @PostMapping("/OkPass")
	    public String OkPass(@RequestParam("email") String email, @RequestParam("newPassword") String newPassword, Model model) {
	   
	      Optional<S_Ditto_MemberEntity> optionalMember = memberService.findMemberIdByEmail(email);
	        
	        if (optionalMember.isPresent()) {
	            S_Ditto_MemberEntity member = optionalMember.get();
	            member.setLoginPw(passwordEncoder.encode(newPassword)); // 비밀번호 암호화 후 설정
	            memberRepository.save(member); // 변경된 비밀번호 저장
	            
	            model.addAttribute("showAlert", true); // 비밀번호 변경 성공 플래그 추가
	        
	            
	            return "pages/admin/login/signin"; // 비밀번호 변경 후 로그인 페이지로 리다이렉트
	        } else {
	            model.addAttribute("showAlert", true); // 이메일이 존재하지 않으면 경고 메시지 표시
	            return "pages/admin/login/findPw"; // 비밀번호 찾기 페이지로 다시 이동
	        }
	    }
	   
	   //비번 재설정, 패스워드 입력 받는 페이지
	    @PostMapping("/updatePassword")
	    public String updatePassword(@RequestParam("email") String email, Model model) {
	       
	        Optional<String> tempPasswordOptional = memberService.findMemberPasswordByEmail(email);
	        
	        if (tempPasswordOptional.isPresent()) {
	            // 이메일이 존재하는 경우
	            String tempPassword = tempPasswordOptional.get();
	            model.addAttribute("email", email);
	            model.addAttribute("tempPassword", tempPassword);
	            return "pages/admin/login/UpdatePassword"; // 비밀번호 변경 페이지로 이동
	        } else {
	            // 이메일이 존재하지 않는 경우
	            model.addAttribute("showAlert", true); // 경고창을 띄우기 위해 showAlert 속성 추가
	            return "pages/admin/login/find"; // 아이디/비밀번호 찾기 페이지로 이동
	        }
	    }
	    
	    //아이디비번 찾기
	    @GetMapping("/find")
	    public String find() {
	        return "pages/admin/login/find";
	    }   

	   
	   // 수정 시, post 로 오는 정보 변환 요청을 처리하는 메서드
	   @PostMapping("/updateMember") 
	   public String updateMember(@ModelAttribute MemberDTO memberDTO, Model model, Principal principal) {
	      
	      System.out.println("수정 요청 함. 요청 한 아이디 --->" + memberDTO);
	      
	       String loginId = principal.getName(); // 현재 로그인된 사용자의 loginId를 가져옴
	       memberDTO.setLoginId(loginId); // 수정할 회원의 loginId 설정
	       memberService.updateArticle(memberDTO); // 서비스의 updateArticle 메서드 호출
	       
	       // 수정 완료 후 마이페이지로 리다이렉트
	       return "redirect:/admin/myPage";
	   }
	    
	    // 개인 정보 수정 페이지
	    @GetMapping("/modify")
	    public String modify(Model model, Principal principal) {
	       
	        String loginId = principal.getName(); // 현재 로그인된 사용자의 loginId를 가져옴
	        MemberDTO member = memberService.getMemberByLoginId(loginId); // 회원 조회
	        model.addAttribute("member", member);

	        System.out.println("수정 페이지 요청함");
	        return "pages/admin/login/modify";
	    }

	
	   //로그인 에러 요청
	   @GetMapping("/signin/error")
	   public String signinError(Model model) {    
	      
	       System.out.println("로그인 페이지에서 에러 요청");
	       
	       model.addAttribute("error", "아이디 또는 비밀번호가 잘못되었습니다.");
	       
	       return "pages/admin/login/signin";
	   }
	   
	   //로그인 페이지
	    @GetMapping("/signin")
	    public String signin() {   
	       System.out.println("로그인 페이지 요청");
	       
	       return "pages/admin/login/signin";
	    }
	    //로그아웃 
	    @GetMapping("/logout")
	    public String logoutConfirm() {
	       System.out.println("로그아웃 요청 됨 ----> ");
	       
	        return "pages/admin/login/logout";
	    }
	    //로그아웃 성공 페이지 
	    @GetMapping("/logoutSuccess")
	    public String logoutSuccess() {
	       System.out.println("로그아웃 성공 페이지 요청");
	        // 로그아웃 성공 페이지로 이동
	        return "pages/admin/login/logoutSuccess";
	    }
	  
	    //회원가입 페이지
	    @GetMapping("/signup")
	    public String signup(Model model) {   
	       System.out.println("회원가입 페이지 요청");  
	       
	       return "pages/admin/login/signup";
	       
	    }
	    
	    // 회원가입 후, Post로 오는 요청을 처리
	    @PostMapping("/joinProc")
	    public String signup(@ModelAttribute MemberDTO memberDTO, Model model) {
	       
	       try {
	            // 회원 정보를 DB에 저장하고 결과를 반환
	            boolean res = memberService.joinProcess(memberDTO);
	            
	            if(res == true && res ) {        
	                // 회원가입이 성공하면 로그인 창으로 성공 알럿과 함께 리다렉
	            	model.addAttribute("joinProcAlert", true);
	                return "pages/admin/login/signin";
	            } else {    
	                // 회원 가입 실패 시, 에러 메시지를 전달하여 다시 회원가입 폼으로
	            	model.addAttribute("joinProcAlert", true);
	                return "pages/admin/login/signup";
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            // 예외 발생 시 에러 메시지를 전달하여 다시 회원가입 폼으로

	            return "pages/admin/login/signup";
	        }
	    }
}
