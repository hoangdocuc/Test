package com.hoangdocuc.controller.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hoangdocuc.dto.CategoryDTO;
import com.hoangdocuc.dto.ProductDTO;
import com.hoangdocuc.service.ICategoryService;
import com.hoangdocuc.service.IProductService;

@Controller(value = "homeControllerOfWeb")
public class HomeController {
	
	@Autowired
	private IProductService productService;
	
	@Autowired
	private ICategoryService categoryService;
	
	@GetMapping(value = "/trang-chu")
	public ModelAndView homePage() {
		
		ModelAndView mav = new ModelAndView("web/home");
		ProductDTO model = new ProductDTO();
		model.setListResult(productService.find8ByView());
		mav.addObject("modelbyView", model);
		ProductDTO model2 = new ProductDTO();
		model2.setListResult(productService.find8ByCreatedDate());
		mav.addObject("modelbyCreatedDate", model2);
		CategoryDTO model3 = new CategoryDTO();
		model3.setListResult(categoryService.find4All());
		mav.addObject("modelCategory4", model3);
		return mav;
	}
	
	@GetMapping(value = "/dang-nhap")
	public ModelAndView loginPage() {
		ModelAndView mav = new ModelAndView("login");
		return mav;
	}
	
	@GetMapping(value = "/thoat")
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return new ModelAndView("redirect:/trang-chu");
	}
	
	@GetMapping(value = "/accessDenied")
	public ModelAndView accessDenied() {
		return new ModelAndView("redirect:/dang-nhap?accessDenied");
	}
}
