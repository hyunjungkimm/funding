package com.study.funding.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.util.ArrayList;


@Entity
@Table(name = "product")
@Getter
@Setter
public class Product {
    @Id
    @Column(name = "product_id")
    @GeneratedValue
    private Long productId;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "target_funding_price")
    private Long targetFundingPrice;
    @Column(name = "total_funding_price")
    private Long totalFundingPrice;
    @Column(name = "funding_status")
    private String fundingStatus;
    @Column(name = "funding_member_id")
    private Long fundingMemberId;
    @Column(name = "start_date")
    private LocalDate startDate;
    @Column(name = "finish_date")
    private LocalDate finishDate;
    @Column(name = "funding_member_number")
    private int fundingMemberNumber = 0;
}
