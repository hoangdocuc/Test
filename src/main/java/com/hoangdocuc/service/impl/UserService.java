package com.hoangdocuc.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hoangdocuc.converter.UserConverter;
import com.hoangdocuc.dto.UserDTO;
import com.hoangdocuc.entity.RoleEntity;
import com.hoangdocuc.entity.UserEntity;
import com.hoangdocuc.repository.RoleRepository;
import com.hoangdocuc.repository.UserRepository;
import com.hoangdocuc.service.IUserService;

@Service
public class UserService implements IUserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserConverter userConverter;
	
	
	@Override
	public List<UserDTO> findAll(Pageable pageable) {
		List<UserDTO> userDTOs = new ArrayList<>();
		List<UserEntity> userEntities = userRepository.findAll(pageable).getContent();
		for (UserEntity userEntity : userEntities) {
			UserDTO userDTO = userConverter.toDTO(userEntity);
			List<String> roles = new ArrayList<>();
			for (RoleEntity role : userEntity.getRoles()) {
				roles.add(role.getCode());
			}
			userDTO.setRoleCode(roles.stream().collect(Collectors.joining()));
			userDTOs.add(userDTO);
		}
		return userDTOs;
	}
	@Override
	@Transactional
	public UserDTO save(UserDTO userDTO) {
		List<RoleEntity> roleEntities = new ArrayList<>();
		RoleEntity roleEntity = roleRepository.findOneByCode(userDTO.getRoleCode());
		roleEntities.add(roleEntity);
		UserEntity newEntity = new UserEntity();
		if(userDTO.getId()!=null) {
			UserEntity oldUser = userRepository.findOne(userDTO.getId());
			newEntity = userConverter.toEntity(oldUser, userDTO);
			newEntity.setRoles(roleEntities);
		} else {
			newEntity = userConverter.toEntity(userDTO);
			newEntity.setRoles(roleEntities);
		}
		return userConverter.toDTO(userRepository.save(newEntity));
	}

	@Override
	public int getTotalItems() {
		return (int) userRepository.count();
	}

	@Override
	public UserDTO findById(long id) {
		UserDTO userDTO = new UserDTO();
		UserEntity userEntity = userRepository.findOne(id);
		userDTO = userConverter.toDTO(userEntity);
		List<String> roles = new ArrayList<>();
		for (RoleEntity role : userEntity.getRoles()) {
			roles.add(role.getCode());
		}
		userDTO.setRoleCode(roles.stream().collect(Collectors.joining()));
		return userDTO;
	}

	

	@Override
	@Transactional
	public void delete(long[] ids) {
		for (long id : ids) {
			userRepository.delete(id);
		}
		
	}
	@Override
	public UserDTO findByUsername(String username) {
		UserDTO userDTO = new UserDTO();
		UserEntity userEntity = userRepository.findOneByUsername(username);
		userDTO = userConverter.toDTO(userEntity);
		List<String> roles = new ArrayList<>();
		for (RoleEntity role : userEntity.getRoles()) {
			roles.add(role.getCode());
		}
		userDTO.setRoleCode(roles.stream().collect(Collectors.joining()));
		return userDTO;
	}

}
