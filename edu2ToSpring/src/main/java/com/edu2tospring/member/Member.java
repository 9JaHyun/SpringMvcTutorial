package com.edu2tospring.member;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer memberId;
    private String id;
    private String password;
    private String name;
    private String email;

    public void update(Member member) {
        this.id = member.getId();
        this.password = member.getPassword();
        this.name = member.getName();
        this.email = member.getEmail();
    }
}
