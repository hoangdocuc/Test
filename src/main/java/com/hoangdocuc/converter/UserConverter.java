package com.hoangdocuc.converter;

import org.springframework.stereotype.Component;

import com.hoangdocuc.dto.UserDTO;
import com.hoangdocuc.entity.UserEntity;

@Component
public class UserConverter {
	
	public UserDTO toDTO(UserEntity entity) {
		UserDTO result = new UserDTO();
		result.setId(entity.getId());
		result.setUsername(entity.getUsername());
		result.setPassword(entity.getPassword());
		result.setEmail(entity.getEmail());
		result.setFullname(entity.getFullname());
		result.setGender(entity.getGender());
		result.setAddress(entity.getAddress());
		result.setPhone(entity.getPhone());;
		result.setNote(entity.getNote());
		result.setStatus(entity.getStatus());
		result.setCreatedDate(entity.getCreatedDate());
		result.setCreatedBy(entity.getCreatedBy());
		result.setModifiedDate(entity.getModifiedDate());
		result.setModifiedBy(entity.getModifiedBy());
		return result;
	}
	
	public UserEntity toEntity(UserDTO dto) {
		UserEntity result = new UserEntity();
		result.setUsername(dto.getUsername());
		result.setPassword(dto.getPassword());
		result.setEmail(dto.getEmail());
		result.setFullname(dto.getFullname());
		result.setGender(dto.getGender());
		result.setAddress(dto.getAddress());
		result.setPhone(dto.getPhone());
		result.setNote(dto.getNote());
		result.setStatus(dto.getStatus());
		return result;
	}
	
	public UserEntity toEntity(UserEntity result, UserDTO dto) {
		result.setUsername(dto.getUsername());
		result.setPassword(dto.getPassword());
		result.setEmail(dto.getEmail());
		result.setFullname(dto.getFullname());
		result.setGender(dto.getGender());
		result.setAddress(dto.getAddress());
		result.setPhone(dto.getPhone());
		result.setNote(dto.getNote());
		result.setStatus(dto.getStatus());
		return result;
	}
}
