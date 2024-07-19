package com.study.funding.repository;

import com.study.funding.entity.Funding;
import com.study.funding.entity.Member;
import com.study.funding.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FundingRepository extends JpaRepository<Funding, Long> {

    List<Funding> findAllByMember(Member member);
}

