package com.hoangdocuc.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hoangdocuc.converter.RoleConverter;
import com.hoangdocuc.dto.RoleDTO;
import com.hoangdocuc.entity.RoleEntity;
import com.hoangdocuc.repository.RoleRepository;
import com.hoangdocuc.service.IRoleService;

@Service
public class RoleService implements IRoleService{
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private RoleConverter roleConverter;

	@Override
	public List<RoleDTO> findRoleByCode(List<String> code) {
		List<RoleDTO> roleDTOs = new ArrayList<>();
		List<RoleEntity> roleEntities = new ArrayList<>();
		for (String codeItem : code) {
			roleEntities.add(roleRepository.findOneByCode(codeItem));
		}
		for (RoleEntity roleEntity : roleEntities) {
			RoleDTO roleDTO = roleConverter.toDTO(roleEntity);
			roleDTOs.add(roleDTO);
		}
		return roleDTOs;
	}

	@Override
	public Map<String, String> findAll() {
		Map<String,String> result = new HashMap<>();
		List<RoleEntity> roleEntities = roleRepository.findAll();
		for (RoleEntity roleEntity : roleEntities) {
			result.put(roleEntity.getCode(), roleEntity.getName());
		}
		return result;
	}

}
