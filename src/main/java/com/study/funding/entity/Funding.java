package com.study.funding.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Entity
@Table(name = "funding")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Funding {

    @Id
    @Column(name = "funding_id", nullable = false)
    @GeneratedValue
    private Long fundingId;

    @Column(name = "funding_price", nullable = false)
    private Long fundingPrice;

    @CreatedDate
    @Column(name = "funding_date")
    private LocalDate fundingDate;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
}
