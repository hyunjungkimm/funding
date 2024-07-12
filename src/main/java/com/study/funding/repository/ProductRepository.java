package com.study.funding.repository;

import com.study.funding.data.FundingStatus;
import com.study.funding.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findProductByFundingStatusAndStartDateLessThanEqualAndFinishDateGreaterThanEqual(String fundingStatus, LocalDate start, LocalDate finish, PageRequest pageRequest);

    Slice<Product> findSliceByFundingStatus(String FundingStatus, Pageable pageable);
}
