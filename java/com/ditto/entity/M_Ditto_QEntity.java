package com.ditto.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity//이 클래스가 엔티티임을 표시
@Getter//필드 값을 외부에서 읽어올 수 있도록 해줌
@Setter
@Builder//Builder 패턴을 자동으로 생성해주는 기능
@AllArgsConstructor//클래스의 모든 필드를 초기화하는 생성자를 자동으로 생성
@NoArgsConstructor//클래스에 기본 생성자를 자동으로 생성
@ToString//객체의 필드 값을 문자열로 표현할 수 있슴
@Table(name = "m_ditto_q")
public class M_Ditto_QEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "m_qna_no")
    private Long mQnaNo;
	
    @Column(name = "login_id")
    private String loginId;
    
    @Column(name = "m_qna_title")
    private String mQnaTitle;

    @Column(name = "m_qna_cntt")
    private String mQnaContent;

    @Column(name = "m_qna_dt")
    private LocalDateTime mQnaDate;

    @Column(name = "m_qna_status")
    private String mQnaStatus;

    @Column(name = "m_qna_q_ans")
    private String mQnaAnswer;

    @Column(name = "m_qna_q_id_dt")
    private LocalDateTime mQnaAnswerDate;
}
