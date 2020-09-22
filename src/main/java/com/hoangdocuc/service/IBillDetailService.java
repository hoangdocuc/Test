package com.hoangdocuc.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.hoangdocuc.dto.BillDetailDTO;
import com.hoangdocuc.entity.BillEntity;

public interface IBillDetailService {
	List<BillDetailDTO> findAll(Pageable pageable);
	BillDetailDTO findById(long id);
	BillDetailDTO save(BillDetailDTO billDetailDTO);
	void delete(long[] ids);
	
	List<BillDetailDTO> findByBill(BillEntity billEntity);
	
}
