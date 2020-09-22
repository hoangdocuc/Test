package com.hoangdocuc.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.hoangdocuc.dto.ProductDTO;

public interface IProductService {
	List<ProductDTO> findAll(Pageable pageable);
	List<ProductDTO> find8ByView();
	List<ProductDTO> find8ByCreatedDate();
	int getTotalItems();
	ProductDTO findById(long id);
	ProductDTO save(ProductDTO productDTO);
	void delete(long[] ids); 
	List<ProductDTO> findbyCategoryId(Long id);
	List<ProductDTO> findbyName(String name);
}
