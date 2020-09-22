package com.hoangdocuc.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hoangdocuc.dto.CategoryDTO;
import com.hoangdocuc.dto.ProductDTO;
import com.hoangdocuc.service.ICategoryService;
import com.hoangdocuc.service.IProductService;

@Controller(value = "productControllerOfWeb")
public class ProductController {
	
	@Autowired
	private IProductService productService;
	
	@Autowired
	private ICategoryService categoryService;
	
	@GetMapping(value = "/san-pham")
	public ModelAndView productHome(@RequestParam (value = "id") Long id) {
		ModelAndView mav = new ModelAndView("web/product");
		
		ProductDTO productDTO = new ProductDTO();
		productDTO = productService.findById(id);
		ProductDTO productDTOnew = productService.findById(id);
		productDTOnew.setView(productDTO.getView()+1);
		productService.save(productDTOnew);
		ProductDTO model2 = new ProductDTO();
		model2.setListResult(productService.find8ByCreatedDate());
		mav.addObject("modelbyCreatedDate", model2);
		CategoryDTO model3 = new CategoryDTO();
		model3.setListResult(categoryService.find4All());
		mav.addObject("modelCategory4", model3);
		mav.addObject("model", productDTO);
		return mav;
	}
	

}
