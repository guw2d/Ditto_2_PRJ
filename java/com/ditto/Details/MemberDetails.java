package com.ditto.Details;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.ditto.entity.S_Ditto_MemberEntity;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MemberDetails implements UserDetails, OAuth2User {

    private final S_Ditto_MemberEntity memberEntity;
    private final PasswordEncoder passwordEncoder;

    public MemberDetails(S_Ditto_MemberEntity memberEntity, PasswordEncoder passwordEncoder) {
        this.memberEntity = memberEntity;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();

        // 사용자의 권한에 따라 ROLE_C, ROLE_S, ROLE_M 설정
        if (memberEntity.getMemberDt().equals("C")) {
            authorities.add(new SimpleGrantedAuthority("ROLE_C"));
        } else if (memberEntity.getMemberDt().equals("S")) {
            authorities.add(new SimpleGrantedAuthority("ROLE_S"));
        } else if (memberEntity.getMemberDt().equals("M")) {
            authorities.add(new SimpleGrantedAuthority("ROLE_M"));
        }

        return authorities;
    }

    @Override
    public String getPassword() {
       
        // 암호화 되지 않은 비밀번호를 반환합니다.
        return memberEntity.getLoginPw();   
    }

    @Override
    public String getUsername() {
        return memberEntity.getLoginId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

}