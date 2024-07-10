package com.study.funding.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;

@Entity
@Table(name = "funding")
@Getter
@Setter
public class Funding {
    @Id
    @Column(name = "funding_id")
    @GeneratedValue
    private Long fundingId;
    @Column(name = "product_id")
    private Long productId;
    @Column(name = "funding_price")
    private Long fundingPrice;
    @Column(name = "member_id")
    private Long memberId;
    @CreatedDate
    @Column(name = "funding_date")
    private LocalDate fundingDate;
}
