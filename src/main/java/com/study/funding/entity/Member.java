package com.study.funding.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "member")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Member {
    @Id
    @Column(name = "member_id", nullable = false)
    @GeneratedValue
    private Long memberId;
    @Column(name = "member_name", nullable = false)
    private String memberName;
    @Column(name = "created_at")
    @CreatedDate
    private LocalDateTime createdAt;

    @JsonManagedReference
    @OneToMany(mappedBy = "member")
    private List<Funding> fundingList = new ArrayList<>();
}
