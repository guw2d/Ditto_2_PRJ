package com.ditto.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ditto.dto.MemberDTO;
import com.ditto.dto.PageRequestDTO;
import com.ditto.dto.PageResultDTO;
import com.ditto.entity.QS_Ditto_MemberEntity;
import com.ditto.entity.S_Ditto_MemberEntity;

import com.ditto.repository.MemberRepository2;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
	

	
	@Autowired
	private final MemberRepository2 memberRepository2;
	
	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public Long register(MemberDTO memberDTO) {//등록
		S_Ditto_MemberEntity entity = dtoToEntity(memberDTO);
		S_Ditto_MemberEntity savedEntity = memberRepository2.save(entity);
		return savedEntity.getMemberNo();
	}

	   // 수정
	   @Override
	   public void updateArticle(MemberDTO memberDTO) {
	      
	       // 주어진 회원 번호를 사용하여 해당 회원을 데이터베이스에서 조회합니다.
	       Optional<S_Ditto_MemberEntity> res = memberRepository2.findByLoginId(memberDTO.getLoginId());
	      
	       // 조회된 회원 정보가 존재하는지 확인합니다.
	       if (res.isPresent()) {
	           // 회원 정보가 존재하는 경우에만 실행됩니다.
	           
	           // 조회된 회원 정보를 가져옵니다.
	           S_Ditto_MemberEntity member = res.get();
	           
	           // 주어진 MemberDTO 객체의 정보로 회원 정보를 업데이트합니다.
	           member.setLoginPw(bCryptPasswordEncoder.encode(memberDTO.getLoginPw())); //비밀번호 암호화
	           member.setMemberAddr(memberDTO.getMemberAddr());//주소
	           member.setMemberNm(memberDTO.getMemberNm());
	           member.setMemberZipcd(memberDTO.getMemberZipcd());//우편번호
	           member.setMemberEmail(memberDTO.getMemberEmail());//이메일
	           member.setMemberTel(memberDTO.getMemberTel());//전화번호
	           
	           // 업데이트된 회원 정보를 데이터베이스에 저장합니다.
	           memberRepository2.save(member);
	       } else {
	           // 조회된 회원 정보가 존재하지 않을 경우에 대한 처리를 추가합니다.
	           throw new NoSuchElementException("해당하는 회원이 존재하지 않습니다. 회원 Id: " + memberDTO.getLoginId());
	       }
	   }

	@Override
	public void delArticle(Long memberNo) {//삭제
		memberRepository2.deleteById(memberNo);
	}

	@Override
	public MemberDTO get(Long memberNo) {//특정 조회
		 Optional<S_Ditto_MemberEntity> optionalMember = memberRepository2.findById(memberNo);
		    if (optionalMember.isPresent()) {
		    	S_Ditto_MemberEntity member = optionalMember.get();
		        return entityToDto(member);
		    } else {
		        // 조회된 회원이 없을 경우 null이 아닌 다른 값이나 예외 처리를 해야 합니다.
		        throw new RuntimeException("해당하는 회원을 찾을 수 없습니다.");
		    }
	}

	@Override
	public List<S_Ditto_MemberEntity> getList() {
		return memberRepository2.findAll();
	}

	@Override
	public PageResultDTO<MemberDTO, S_Ditto_MemberEntity> m_getList(PageRequestDTO pageRequestDTO) {
		Pageable pageable = pageRequestDTO.getPageable(Sort.by("memberNo").descending());
		BooleanBuilder booleanBuilder = getSearch(pageRequestDTO);//etSearch 메서드는 검색 조건을 생성하는 역할
		Page<S_Ditto_MemberEntity> result = memberRepository2.findAll(pageable);
		
		Function<S_Ditto_MemberEntity, MemberDTO> fn = this::entityToDto;
		
		return new PageResultDTO<>(result, fn);
	}

	private BooleanBuilder getSearch(PageRequestDTO requestDTO) {
		String type = requestDTO.getType();
		BooleanBuilder booleanBuilder = new BooleanBuilder();

		QS_Ditto_MemberEntity qS_Ditto_Member = QS_Ditto_MemberEntity.s_Ditto_MemberEntity;

		String keyword = requestDTO.getKeyword();

		BooleanExpression exp = qS_Ditto_Member.memberNo.gt(0L); // 1번 글 이상부터 검색 대상으로 시작함
		booleanBuilder.and(exp);

		if (type == null || keyword.isEmpty()) { // 검색 조건이 없음..
		    return booleanBuilder;
		}

		// 검색조건 작성
		BooleanBuilder booleanBuilder2 = new BooleanBuilder();

		if ("l".equals(type)) { // 제목으로 검색을 요청했을 경우
		    booleanBuilder2.or(qS_Ditto_Member.loginId.contains(keyword));
		}
		if ("n".equals(type)) { // 내용으로 검색 요청시
		    booleanBuilder2.or(qS_Ditto_Member.memberNm.contains(keyword));
		}
		if ("e".equals(type)) { // 작성자로 검색 요청시
		    booleanBuilder2.or(qS_Ditto_Member.memberEmail.contains(keyword));
		}

		// 위 조건 모두 통합
		booleanBuilder.and(booleanBuilder2);

		return booleanBuilder;
	}

	//회원가입
	@Override
	@Transactional 
	public boolean joinProcess(MemberDTO memberDto) {
		
		// 이미 회원가입이 된 ID 가 있는지 조회.
        S_Ditto_MemberEntity memberEntity = memberRepository2.findByLoginId(memberDto.getLoginId())
            .orElse(null); 
       
        if(memberEntity != null) {
        	// 동일한 ID 존재
        	return false;
        	
        }else {
        	// 존재하지 않는 경우, 회원 정보를 생성하여 저장
            S_Ditto_MemberEntity entity = new S_Ditto_MemberEntity();

            
            entity.setLoginId(memberDto.getLoginId());
            entity.setLoginPw(bCryptPasswordEncoder.encode(memberDto.getLoginPw())); //비밀번호 암호화
            entity.setMemberDt(memberDto.getMemberDt()); // 회원 구분 설정
            entity.setMemberNm(memberDto.getMemberNm());
            entity.setMemberEmail(memberDto.getMemberEmail());
            entity.setMemberAddr(memberDto.getMemberAddr());
            entity.setMemberZipcd(memberDto.getMemberZipcd());
            entity.setMemberTel(memberDto.getMemberTel());
            
            
            entityToDto(entity);
            // 회원 저장
            memberRepository2.save(entity);
            
            return true;
        }
    }
        
	
	
	// post 로그인 요청 시 회원정보 일치 시, 로그인 됨 
	@Override
    public boolean authenticate(String loginId, String loginPw) {
		
		// 데이터베이스에서 제공된 loginId를 기반으로 사용자 세부 정보를 가져옵니다.
        S_Ditto_MemberEntity memberEntity = memberRepository2.findByLoginId(loginId)
            .orElse(null);
        
        // 회원 정보가 존재하고, 비밀번호가 일치하는지 확인합니다.
        if (memberEntity != null && bCryptPasswordEncoder.matches(loginPw, memberEntity.getLoginPw())) {
        	
        	return true;
        	
        } else {
            // 인증 실패
        	throw new RuntimeException("해당하는 회원을 찾을 수 없습니다." + memberEntity.getLoginId());
        
        }
    }

	   //아이디 조회
	   @Override
	    public MemberDTO getMemberByLoginId(String loginId) {
	        Optional<S_Ditto_MemberEntity> optionalMember = memberRepository2.findByLoginId(loginId);
	        
	        if (optionalMember.isPresent()) {
	            S_Ditto_MemberEntity member = optionalMember.get();
	            return entityToDto(member);
	        } else {
	            throw new RuntimeException("해당하는 회원을 찾을 수 없습니다.");
	        }
	    }
	   
     @Override
     // 이메일로 회원 아이디를 조회하는 메서드
    public Optional<S_Ditto_MemberEntity> findMemberIdByEmail(String memberEmail) {
        return memberRepository2.findByMemberEmail(memberEmail);
    }

    // 이메일로 회원 비밀번호를 조회하는 메서드
    @Override
    public Optional<String> findMemberPasswordByEmail(String memberEmail) {
        Optional<S_Ditto_MemberEntity> memberOptional = memberRepository2.findByMemberEmail(memberEmail);
        if (memberOptional.isPresent()) {
            S_Ditto_MemberEntity member = memberOptional.get();
            return Optional.of(member.getLoginPw());
        } else {
            return Optional.empty();
        }
    }

	 // 아이디로 회원 조회 메서드 구현
    @Override
    public Optional<S_Ditto_MemberEntity> findMemberByLoginId(String loginId) {
        return memberRepository2.findByLoginId(loginId);
    }

    // 회원 탈퇴 메서드 구현
    @Override
    public void deleteMember(String loginId, String loginPw) {
        // 입력한 아이디로 회원을 조회합니다.
        Optional<S_Ditto_MemberEntity> optionalMember = memberRepository2.findByLoginId(loginId);
        
        if (optionalMember.isPresent()) { // 조회된 회원이 존재하는 경우
            S_Ditto_MemberEntity member = optionalMember.get();
            // 입력한 비밀번호와 회원의 비밀번호를 비교합니다.
            if (bCryptPasswordEncoder.matches(loginPw, member.getLoginPw())) { // 비밀번호가 일치하는 경우
                // 회원을 삭제합니다.
                memberRepository2.delete(member);
            } else { // 비밀번호가 일치하지 않는 경우
                // 예외를 발생시킵니다.
                throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
            }
        } else { // 조회된 회원이 없는 경우
            // 예외를 발생시킵니다.
            throw new IllegalArgumentException("해당하는 회원을 찾을 수 없습니다.");
        }
    }


}
