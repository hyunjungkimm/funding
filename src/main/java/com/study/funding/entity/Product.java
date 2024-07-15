package com.study.funding.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "product")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Product {
    @Id
    @Column(name = "product_id", nullable = false)
    @GeneratedValue
    private Long productId;
    @Column(name = "product_name", nullable = false)
    private String productName;
    @Column(name = "target_funding_price", nullable = false)
    private Long targetFundingPrice;
    @Column(name = "total_funding_price", nullable = false)
    private Long totalFundingPrice;
    @Column(name = "funding_status", nullable = false, length = 30)
    private String fundingStatus;
    @Column(name = "start_date", nullable = false)
    @CreatedDate
    private LocalDate startDate;
    @Column(name = "finish_date", nullable = false)
    private LocalDate finishDate;
    @Column(name = "funding_member_number", nullable = false)
    private int fundingMemberNumber = 0;

    @JsonManagedReference
    @OneToMany(mappedBy = "product")
    private List<Funding> fundingList = new ArrayList<>();
}
