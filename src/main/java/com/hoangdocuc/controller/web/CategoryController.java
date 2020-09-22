package com.hoangdocuc.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hoangdocuc.dto.CategoryDTO;
import com.hoangdocuc.dto.ProductDTO;
import com.hoangdocuc.service.ICategoryService;
import com.hoangdocuc.service.IProductService;

@Controller(value = "categoryControllerOfWeb")
public class CategoryController {
	
	@Autowired
	private IProductService productService;
	
	@Autowired
	private ICategoryService categoryService;
	
	@GetMapping(value="/danh-muc")
	public ModelAndView showList(@RequestParam (value = "id",required = false) Long id,
								@RequestParam (value = "name",required = false) String name) {
		ProductDTO model = new ProductDTO();
		ModelAndView mav = new ModelAndView("web/category");
		if(id==null) {
			if(name==null) {
				Pageable pageable = new PageRequest(0, 12);
				model.setListResult(productService.findAll(pageable));
			} else {
				model.setListResult(productService.findbyName(name));
			}
		} else {
			model.setListResult(productService.findbyCategoryId(id));
		}
		
		CategoryDTO model3 = new CategoryDTO();
		model3.setListResult(categoryService.findAllCategory());
		mav.addObject("modelCategory", model3);
		CategoryDTO model4 = new CategoryDTO();
		model4.setListResult(categoryService.find4All());
		mav.addObject("modelCategory4", model4);
		mav.addObject("model", model);
		return mav;
	}

}
