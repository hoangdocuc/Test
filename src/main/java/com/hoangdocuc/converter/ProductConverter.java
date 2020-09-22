package com.hoangdocuc.converter;

import org.springframework.stereotype.Component;

import com.hoangdocuc.dto.ProductDTO;
import com.hoangdocuc.entity.ProductEntity;


@Component
public class ProductConverter {
	
	public ProductDTO toDTO(ProductEntity entity) {
		ProductDTO result = new ProductDTO();
		result.setId(entity.getId());
		result.setName(entity.getName());
		result.setImage(entity.getImage());
		result.setPrice(entity.getPrice());
		result.setQuantity(entity.getQuantity());
		result.setSize(entity.getSize());
		result.setInformation(entity.getInformation());
		result.setPrice(entity.getPrice());
		result.setView(entity.getView());
		result.setCategoryCode(entity.getCategory().getCode());
		result.setCreatedDate(entity.getCreatedDate());
		result.setCreatedBy(entity.getCreatedBy());
		result.setModifiedDate(entity.getModifiedDate());
		result.setModifiedBy(entity.getModifiedBy());
		return result;
	}
	public ProductEntity toEntity(ProductDTO dto) {
		ProductEntity result = new ProductEntity();
		result.setName(dto.getName());
		result.setImage(dto.getImage());
		result.setSize(dto.getSize());
		result.setQuantity(dto.getQuantity());
		result.setInformation(dto.getInformation());
		result.setPrice(dto.getPrice());
		result.setView(dto.getView());
		return result;
	}
	public ProductEntity toEntity(ProductEntity result,ProductDTO dto) {
		result.setName(dto.getName());
		result.setImage(dto.getImage());
		result.setSize(dto.getSize());
		result.setQuantity(dto.getQuantity());
		result.setInformation(dto.getInformation());
		result.setPrice(dto.getPrice());
		result.setView(dto.getView());
		return result;
	}

}
