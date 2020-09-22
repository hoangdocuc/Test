package com.hoangdocuc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hoangdocuc.entity.BillDetailEntity;
import com.hoangdocuc.entity.BillEntity;

public interface BillDetailRepository extends JpaRepository<BillDetailEntity, Long>{
	List<BillDetailEntity> findByBill(BillEntity billEntity);
}
