package com.hoangdocuc.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.hoangdocuc.converter.ProductConverter;
import com.hoangdocuc.dto.ProductDTO;
import com.hoangdocuc.entity.CategoryEntity;
import com.hoangdocuc.entity.ProductEntity;
import com.hoangdocuc.repository.CategoryReposiory;
import com.hoangdocuc.repository.ProductRepository;
import com.hoangdocuc.service.IProductService;

@Service
public class ProductService implements IProductService{
	
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ProductConverter productConverter;
	
	@Autowired
	private CategoryReposiory categoryReposiory;
	
	@Override
	public List<ProductDTO> findAll(Pageable pageable) {
		List<ProductDTO> productDTOs = new ArrayList<>(); 
		List<ProductEntity> productEntities = productRepository.findAll(pageable).getContent();
		for (ProductEntity productEntity : productEntities) {
			ProductDTO productDTO = productConverter.toDTO(productEntity);
			productDTOs.add(productDTO);
		}
		return productDTOs;
	}

	@Override
	@Transactional
	public ProductDTO save(ProductDTO productDTO) {
		CategoryEntity category = categoryReposiory.findOneByCode(productDTO.getCategoryCode());
		ProductEntity newProduct = new ProductEntity();
		if(productDTO.getId()!=null) {
			ProductEntity oldProduct = productRepository.findOne(productDTO.getId());
			oldProduct.setCategory(category);
			newProduct = productConverter.toEntity(oldProduct,productDTO);
		} else {
			newProduct = productConverter.toEntity(productDTO);		
			newProduct.setCategory(category);
		}
		return productConverter.toDTO(productRepository.save(newProduct));
	}

	@Override
	public int getTotalItems() {
		return (int) productRepository.count();
	}

	@Override
	public ProductDTO findById(long id) {
		ProductEntity productEntity = productRepository.findOne(id);
		return productConverter.toDTO(productEntity);
	}

	@Override
	@Transactional
	public void delete(long[] ids) {
		for (long id : ids) {
			productRepository.delete(id);
		}
	}

	@Override
	public List<ProductDTO> find8ByView() {
		Sort sort = new Sort(Direction.DESC,"view");
		Pageable pageable = new PageRequest(0,8,sort);
		List<ProductDTO> productDTOs = new ArrayList<>(); 
		List<ProductEntity> productEntities = productRepository.findAll(pageable).getContent();
		for (ProductEntity productEntity : productEntities) {
			ProductDTO productDTO = productConverter.toDTO(productEntity);
			productDTOs.add(productDTO);
		}
		return productDTOs;
	}

	@Override
	public List<ProductDTO> find8ByCreatedDate() {
		Sort sort = new Sort(Direction.DESC,"createdDate");
		Pageable pageable = new PageRequest(0,8,sort);
		List<ProductDTO> productDTOs = new ArrayList<>(); 
		List<ProductEntity> productEntities = productRepository.findAll(pageable).getContent();
		for (ProductEntity productEntity : productEntities) {
			ProductDTO productDTO = productConverter.toDTO(productEntity);
			productDTOs.add(productDTO);
		}
		return productDTOs;
	}

	@Override
	public List<ProductDTO> findbyCategoryId(Long id) {
		CategoryEntity category = new CategoryEntity();
		category = categoryReposiory.findOne(id);
		List<ProductDTO> productDTOs = new ArrayList<>(); 
		List<ProductEntity> productEntities = productRepository.findByCategory(category);
		for (ProductEntity productEntity : productEntities) {
			ProductDTO productDTO = productConverter.toDTO(productEntity);
			productDTOs.add(productDTO);
		}
		return productDTOs;
	}

	@Override
	public List<ProductDTO> findbyName(String name) {
		List<ProductDTO> productDTOs = new ArrayList<>(); 
		List<ProductEntity> productEntities = productRepository.findByNameContaining(name);
		for (ProductEntity productEntity : productEntities) {
			ProductDTO productDTO = productConverter.toDTO(productEntity);
			productDTOs.add(productDTO);
		}
		return productDTOs;
	}

	
	
}
