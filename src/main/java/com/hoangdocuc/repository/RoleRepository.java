package com.hoangdocuc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hoangdocuc.entity.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Long>{
	RoleEntity findOneByCode(String code);
}
