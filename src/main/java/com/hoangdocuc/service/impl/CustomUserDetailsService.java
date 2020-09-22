package com.hoangdocuc.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hoangdocuc.constant.SystemConstant;
import com.hoangdocuc.dto.MyUser;
import com.hoangdocuc.entity.RoleEntity;
import com.hoangdocuc.entity.UserEntity;
import com.hoangdocuc.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.findOneByUsernameAndStatus(username, SystemConstant.ACTIVE_STATUS);

		if (userEntity == null) {
			throw new UsernameNotFoundException("User not found");
		}
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (RoleEntity role : userEntity.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(role.getCode()));
		}
		// put thong tin vao security duy tri thong tin dang nhap he thong
		MyUser myuser = new MyUser(userEntity.getUsername(), userEntity.getPassword(), true, true, true, true,
				authorities);
		myuser.setFullname(userEntity.getFullname());

		return myuser;
	}

}
