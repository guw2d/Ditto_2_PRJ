package com.ditto.config;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import com.ditto.Details.MemberDetailsService;
import com.ditto.repository.MemberRepository2;

import jakarta.servlet.DispatcherType;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



@Configuration
@EnableWebSecurity // Spring Security 설정 활성화
public class SecurityConfig {

	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http, MemberDetailsService memberDetailsService) throws Exception {
	    return http.csrf(csrf -> csrf.disable()).cors(cors -> cors.disable())

	            // 로그인 설정
	            .formLogin(login -> login
	                    .loginPage("/admin/signin") // 커스텀 로그인 페이지 지정
	                    .loginProcessingUrl("/loginProc") // submit 받을 url
	                    .usernameParameter("loginId") // submit할 아이디
	                    .passwordParameter("loginPw") // submit할 비밀번호
	                    .defaultSuccessUrl("/admin/main", true) // loginOK 로 요청 get 매핑임
	                    .successHandler(authenticationSuccessHandler()) // 로그인 성공 핸들러 등록
	                    .failureHandler(authenticationFailureHandler()) // 로그인 실패 핸들러
	                    .permitAll())

	            // 페이지 권한 설정
	            .authorizeHttpRequests(auth -> auth
	            		.requestMatchers("/admin/**").permitAll() // 어드민 페이지는 모두 접근 가능
	            		
	            		
	            		
	            		.requestMatchers("/display/**").permitAll() // /display 경로에 대한 접근 허용
	            		.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                        .requestMatchers(HttpMethod.POST, "/uploadAjax").permitAll()
                        .requestMatchers(HttpMethod.POST, "/removeFile").permitAll()
                        .requestMatchers(HttpMethod.GET, "/display").permitAll()
	            	    .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
	            	    //index 수정 필요
	            	    .requestMatchers("/**","/mdcss/**", "/lib/**", "/img/**", "/fonts/**", "/testimg/**","/css2/**", "/icon/**","/images/**", "/css/**", "/js/**","/ditto/**").permitAll() // 특정 경로는 모두 허용
	            	    .requestMatchers("/admin/myPage").authenticated() // 로그인 한 회원만 접근 가능, 비회원일시 로그인 페이지로 리다이렉션
	            	    .requestMatchers("/master/**").hasRole("M") // 마스터만 접근 가능
	            	    .requestMatchers("/seller/**").hasRole("S") // 셀러만 접근 가능            	    
	            )
	            
	            //소셜 로그인 설정
	            .oauth2Login(oath->oath.permitAll())
				.oauth2Login(login->login.successHandler(authenticationSuccessHandler()))
                

	            // 로그아웃 설정
                .logout(logout -> logout
                        .logoutUrl("/admin/logout") // 로그아웃 URL 설정
                        .logoutSuccessHandler((request, response, authentication) -> {
                            response.sendRedirect("/admin/logoutSuccess");
                        }) // 로그아웃 성공 시 핸들러 설정
                        .invalidateHttpSession(true) // 세션 무효화
                        .deleteCookies("JSESSIONID") // 쿠키 삭제
                        .permitAll())
                .build();
	    
	    
	}
	
	


	//로그인 실패 핸들러
	private AuthenticationFailureHandler authenticationFailureHandler() {
		return (request, response, exception) -> {
			
			// 로그인 실패 시, 메세지 담은 알럿창 띄움
	        response.setContentType("text/html;charset=UTF-8");
	        PrintWriter out = response.getWriter();
	        out.println("<script>alert('로그인에 실패했습니다. 아이디 또는 비밀번호를 확인해주세요.'); window.location.href='/admin/signin';</script>");
	        out.flush();
	        out.close();
		};
	}	


	//비밀번호 암호화
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public MemberDetailsService memberDetailsService(MemberRepository2 memberRepository, PasswordEncoder passwordEncoder) {
        return new MemberDetailsService(memberRepository, passwordEncoder);
    }

    
    //로그인 성공 후 핸들러 
    private AuthenticationSuccessHandler authenticationSuccessHandler() {
       
        return new SimpleUrlAuthenticationSuccessHandler() {
           
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
               
                Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
                String redirectUrl = "/admin/main"; // 기본 권한인 어드민 메인

                for (GrantedAuthority authority : authorities) {
                   
                    if (authority.getAuthority().equals("ROLE_M")) { //마스터 메인
                       
                        System.out.println("로그인 시, 마스터 페이지로 로그인 함 ");
                        redirectUrl = "/master/main";
                        break;
                    } else if (authority.getAuthority().equals("ROLE_S")) { //셀러 메인
                       
                        System.out.println("로그인 시, 셀러 페이지로 로그인 함 ");
                        redirectUrl = "/seller/main";
                        break;
                    }
                }
                
                //로그인 성공 시, 성공 메세지 알럿 창 띄운 후, 권한에 맞는 메인 페이지로 리다렉션
                response.setContentType("text/html;charset=UTF-8");
                PrintWriter out = response.getWriter();
                out.println("<script>alert('로그인에 성공했습니다.'); window.location.href='" + redirectUrl + "';</script>");
                out.flush();
                out.close();
            }
        };
    }
}