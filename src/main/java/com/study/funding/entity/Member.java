package com.study.funding.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "member")
@Getter
@Setter
public class Member {
    @Id
    @Column(name = "member_id")
    @GeneratedValue
    private Long memberId;
    @Column(name = "member_name")
    private String memberName;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
