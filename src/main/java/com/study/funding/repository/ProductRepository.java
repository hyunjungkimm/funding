package com.study.funding.repository;

import com.study.funding.entity.Product;
import jakarta.persistence.LockModeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findProductByFundingStatusAndStartDateLessThanEqualAndFinishDateGreaterThanEqual(String fundingStatus, LocalDate start, LocalDate finish, PageRequest pageRequest);

    Slice<Product> findSliceByFundingStatus(String FundingStatus, Pageable pageable);


    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Product> findByProductId(long productId);
}
