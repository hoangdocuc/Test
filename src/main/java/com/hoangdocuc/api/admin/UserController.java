package com.hoangdocuc.api.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hoangdocuc.dto.UserDTO;
import com.hoangdocuc.service.IUserService;

@RestController(value = "userAPIOfAdmin")
public class UserController {
	
	@Autowired
	private IUserService iUserService;
	
	@PostMapping(value="/api/tai-khoan")
	public UserDTO createUserDTO(@RequestBody UserDTO userDTO) {
		return iUserService.save(userDTO);
	}
	
	@PutMapping(value="/api/tai-khoan")
	public UserDTO updateUserDTO(@RequestBody UserDTO userDTO) {
		return iUserService.save(userDTO);
	}
	
	@DeleteMapping(value="/api/tai-khoan")
	public void deleteUser(@RequestBody long[] ids) {
		iUserService.delete(ids);
	}

}
