package com.hoangdocuc.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.hoangdocuc.dto.BillDTO;

public interface IBillService {
	List<BillDTO> findAll(Pageable pageable);
	int getTotalItems();
	BillDTO findById(long id);
	BillDTO save(BillDTO billDTO);
	void delete(long[] ids);

}
