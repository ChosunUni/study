package com.suntime.study.entity;

import com.suntime.study.dto.MemberDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
// Entity란 실제 DB에 매칭되는 클래스를 의미한다
@Entity
@Setter
@Getter
@Table(name = "member_table")
public class MemberEntity {
    @Id // pk 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private int idx;

    @Column(unique = true, nullable = false, length = 30)
    private String memberEmail;

    @Column(nullable = false, length = 255)
    private String memberPW;

    @Column(nullable = false, length = 10)
    private String memberName;

    @Column(nullable = false, columnDefinition = "int(1) default 0")
    private int authority = 0;

    @NotNull
    @Column(nullable = false, columnDefinition = "int(1) default 0")
    private int memberEmailCheck = 0;

    public static MemberEntity toMemberEntity(MemberDTO memberDTO){
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setMemberEmail(memberDTO.getMemberEmail());
        memberEntity.setMemberPW(memberDTO.getMemberPW());
        memberEntity.setMemberName(memberDTO.getMemberName());
        memberEntity.setAuthority(memberDTO.getAuthority());
        memberEntity.setMemberEmailCheck(memberDTO.getMemberEmailCheck());

        return memberEntity;
    }
}
