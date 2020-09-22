package com.hoangdocuc.converter;

import org.springframework.stereotype.Component;

import com.hoangdocuc.dto.BillDTO;
import com.hoangdocuc.entity.BillEntity;

@Component
public class BillConverter {
	
	public BillDTO toDTO(BillEntity entity) {
		BillDTO result = new BillDTO();
		result.setId(entity.getId());
		result.setNote(entity.getNote());
		result.setStatus(entity.getStatus());
		result.setCreatedDate(entity.getCreatedDate());
		result.setCreatedBy(entity.getCreatedBy());
		result.setModifiedDate(entity.getModifiedDate());
		result.setModifiedBy(entity.getModifiedBy());
		result.setUserId(entity.getUser().getId());
		result.setFullname(entity.getUser().getFullname());
		return result;
	}
	
	public BillEntity toEntity(BillDTO dto) {
		BillEntity result = new BillEntity();
		result.setNote(dto.getNote());
		result.setStatus(dto.getStatus());
		return result;
	}
	public BillEntity toEntity(BillEntity result,BillDTO dto) {
		result.setNote(dto.getNote());
		result.setStatus(dto.getStatus());
		return result;
	}

}
