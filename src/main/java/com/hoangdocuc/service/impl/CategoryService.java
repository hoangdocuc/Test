package com.hoangdocuc.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hoangdocuc.converter.CategoryConverter;
import com.hoangdocuc.dto.CategoryDTO;
import com.hoangdocuc.entity.CategoryEntity;
import com.hoangdocuc.repository.CategoryReposiory;
import com.hoangdocuc.service.ICategoryService;

@Service
public class CategoryService implements ICategoryService{
	
	@Autowired
	private CategoryReposiory categoryReposiory;
	
	@Autowired
	private CategoryConverter categoryConverter;

	@Override
	public Map<String,String> findAll() {
		Map<String,String> result = new HashMap<>();
		List<CategoryEntity> categoryEntities = categoryReposiory.findAll();
		for(CategoryEntity categoryEntity: categoryEntities) {
			result.put(categoryEntity.getCode(), categoryEntity.getName());
		}
		return result;
	}

	@Override
	public List<CategoryDTO> findAll(Pageable pageable) {
		List<CategoryDTO> categoryDTOs = new ArrayList<>();
		List<CategoryEntity> categoryEntities = categoryReposiory.findAll(pageable).getContent();
		for(CategoryEntity categoryEntity : categoryEntities) {
			CategoryDTO categoryDTO = categoryConverter.toDTO(categoryEntity);
			categoryDTOs.add(categoryDTO);
		}
		return categoryDTOs;
	}
	
	@Override
	@Transactional
	public CategoryDTO save(CategoryDTO categoryDTO) {
		CategoryEntity newCategory = new CategoryEntity();
		if(categoryDTO.getId()!=null) {
			CategoryEntity oldCategory = categoryReposiory.findOne(categoryDTO.getId());
			newCategory = categoryConverter.toEntity(oldCategory, categoryDTO);
		} else {
			newCategory = categoryConverter.toEntity(categoryDTO);
		}
		return categoryConverter.toDTO(categoryReposiory.save(newCategory));
	}

	@Override
	public int getTotalItems() {
		return (int) categoryReposiory.count();
	}

	@Override
	public CategoryDTO findById(long id) {
		CategoryEntity categoryEntity = categoryReposiory.findOne(id);
		return categoryConverter.toDTO(categoryEntity);
	}

	@Override
	@Transactional
	public void delete(long[] ids) {
		for(long id : ids) {
			categoryReposiory.delete(id);
		}
		
	}

	@Override
	public List<CategoryDTO> find4All() {
		Pageable pageable = new PageRequest(0,4);
		List<CategoryDTO> categoryDTOs = new ArrayList<>();
		List<CategoryEntity> categoryEntities = categoryReposiory.findAll(pageable).getContent();
		for(CategoryEntity categoryEntity : categoryEntities) {
			CategoryDTO categoryDTO = categoryConverter.toDTO(categoryEntity);
			categoryDTOs.add(categoryDTO);
		}
		return categoryDTOs;
	}

	@Override
	public List<CategoryDTO> findAllCategory() {
		List<CategoryDTO> categoryDTOs = new ArrayList<>();
		List<CategoryEntity> categoryEntities = categoryReposiory.findAll();
		for(CategoryEntity categoryEntity : categoryEntities) {
			CategoryDTO categoryDTO = categoryConverter.toDTO(categoryEntity);
			categoryDTOs.add(categoryDTO);
		}
		return categoryDTOs;
	}


}
