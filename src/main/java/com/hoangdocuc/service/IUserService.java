package com.hoangdocuc.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.hoangdocuc.dto.UserDTO;

public interface IUserService {
	List<UserDTO> findAll(Pageable pageable);
	int getTotalItems();
	UserDTO findById(long id);
	UserDTO save(UserDTO userDTO);
	void delete(long[] ids);
	UserDTO findByUsername(String username);

}
