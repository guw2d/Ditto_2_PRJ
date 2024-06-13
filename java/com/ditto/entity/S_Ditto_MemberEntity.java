package com.ditto.entity;

import java.util.HashSet;
import java.util.Set;

import com.ditto.model.Role;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "s_ditto_member")
public class S_Ditto_MemberEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_no")
    private Long memberNo;

    @Column(name = "login_id", nullable = false, unique = true)
    private String loginId;

    @Column(name = "login_pw", nullable = false)
    private String loginPw;

    @Column(name = "member_nm", nullable = false)
    private String memberNm;

    @Column(name = "member_email", nullable = false, unique = true)
    private String memberEmail;

    @Column(name = "member_addr", nullable = false)
    private String memberAddr;

    @Column(name = "member_zipcd", nullable = false)
    private String memberZipcd;

    @Column(name = "member_tel", nullable = false)
    private String memberTel;

    @Column(name = "member_dt", nullable = false)
    private String memberDt;

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private Set<Role> roles = new HashSet<>();
}