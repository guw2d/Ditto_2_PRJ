package com.ditto.Details;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ditto.entity.S_Ditto_MemberEntity;
import com.ditto.repository.MemberRepository2;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberDetailsService implements UserDetailsService {
   
   private final MemberRepository2 memberRepository;
    private final PasswordEncoder passwordEncoder; // PasswordEncoder 주입
    
   @Override
   public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
      
      S_Ditto_MemberEntity member = memberRepository.findByUserId(loginId);
      
      System.out.println("디테일 로그인 요청 됨------>" + loginId);
      
      if (member == null) {
            throw new UsernameNotFoundException("회원정보 없음");
        }

        // PasswordEncoder를 사용하여 생성자로 주입된 passwordEncoder를 전달합니다.
       return new MemberDetails(member, passwordEncoder);
   }
   
}