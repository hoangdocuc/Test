package com.hoangdocuc.service;

import java.util.List;
import java.util.Map;

import com.hoangdocuc.dto.RoleDTO;

public interface IRoleService {
	List<RoleDTO> findRoleByCode(List<String> code); 
	Map<String,String> findAll();
}
