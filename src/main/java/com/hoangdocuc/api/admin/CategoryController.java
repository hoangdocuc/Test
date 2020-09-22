package com.hoangdocuc.api.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hoangdocuc.dto.CategoryDTO;
import com.hoangdocuc.service.ICategoryService;

@RestController(value = "categoryAPIOfAdmin")
public class CategoryController {
	
	@Autowired
	private ICategoryService iCategoryService;
	
	@PostMapping(value="/api/danh-muc")
	public CategoryDTO createCategory(@RequestBody CategoryDTO categoryDTO) {
		return iCategoryService.save(categoryDTO);
	}
	@PutMapping(value="/api/danh-muc")
	public CategoryDTO updateCategory(@RequestBody CategoryDTO categoryDTO) {
		return iCategoryService.save(categoryDTO);
	}
	@DeleteMapping(value="/api/danh-muc")
	public void deleteCategory(@RequestBody long[] ids) {
		iCategoryService.delete(ids);
	}
	
}
