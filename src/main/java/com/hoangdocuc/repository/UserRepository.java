package com.hoangdocuc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hoangdocuc.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long>{
	UserEntity findOneByUsernameAndStatus(String name,int status);
	UserEntity findOneByUsername(String username);
	
}
