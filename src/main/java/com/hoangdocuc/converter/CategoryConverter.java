package com.hoangdocuc.converter;

import org.springframework.stereotype.Component;

import com.hoangdocuc.dto.CategoryDTO;
import com.hoangdocuc.entity.CategoryEntity;

@Component
public class CategoryConverter {
	
	public CategoryDTO toDTO(CategoryEntity entity) {
		CategoryDTO result = new CategoryDTO();
		result.setId(entity.getId());
		result.setCode(entity.getCode());
		result.setName(entity.getName());
		result.setCreatedDate(entity.getCreatedDate());
		result.setCreatedBy(entity.getCreatedBy());
		result.setModifiedDate(entity.getModifiedDate());
		result.setModifiedBy(entity.getModifiedBy());
		return result;
	}
	
	public CategoryEntity toEntity(CategoryDTO dto) {
		CategoryEntity result = new CategoryEntity();
		result.setCode(dto.getCode());
		result.setName(dto.getName()); 
		return result;
	}
	
	public CategoryEntity toEntity(CategoryEntity result,CategoryDTO dto) {
		result.setCode(dto.getCode());
		result.setName(dto.getName());
		return result;
	}

}
