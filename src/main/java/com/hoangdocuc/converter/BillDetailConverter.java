package com.hoangdocuc.converter;

import org.springframework.stereotype.Component;

import com.hoangdocuc.dto.BillDetailDTO;
import com.hoangdocuc.entity.BillDetailEntity;

@Component
public class BillDetailConverter {
	
	public BillDetailDTO toDTO(BillDetailEntity entity) {
		BillDetailDTO result = new BillDetailDTO();
		result.setId(entity.getId());
		result.setQuantity(entity.getQuantity());
		result.setSize(entity.getSize());
		result.setBillId(entity.getBill().getId());
		result.setProductId(entity.getProduct().getId());
		result.setProductImage(entity.getProduct().getImage());
		result.setProductName(entity.getProduct().getName());
		return result;
	}
	
	public BillDetailEntity toEntity(BillDetailDTO dto) {
		BillDetailEntity result = new BillDetailEntity();
		result.setQuantity(dto.getQuantity());
		result.setSize(dto.getSize());
		return result;
	}
	
	public BillDetailEntity toEntity(BillDetailEntity result,BillDetailDTO dto) {
		result.setQuantity(dto.getQuantity());
		result.setSize(dto.getSize());
		return result;
	}
	
	
}
