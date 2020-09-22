package com.hoangdocuc.converter;

import org.springframework.stereotype.Component;

import com.hoangdocuc.dto.RoleDTO;
import com.hoangdocuc.entity.RoleEntity;

@Component
public class RoleConverter {
	
	public RoleDTO toDTO(RoleEntity entity) {
		RoleDTO result = new RoleDTO();
		result.setId(entity.getId());
		result.setName(entity.getName());
		result.setCode(entity.getCode());
		return result;
	}
	public RoleEntity toEntity(RoleDTO dto) {
		RoleEntity result = new RoleEntity();
		result.setName(dto.getName());
		result.setCode(dto.getCode());
		return result;
	}
	
}
