package com.hoangdocuc.controller.admin;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hoangdocuc.dto.ProductDTO;
import com.hoangdocuc.service.ICategoryService;
import com.hoangdocuc.service.IProductService;
import com.hoangdocuc.util.MessageUtil;

@Controller(value = "productControllerOfAdmin")
public class ProductController {
	
	@Autowired
	private IProductService productService;
	
	@Autowired
	private ICategoryService categoryService;
	
	@Autowired
	private MessageUtil messageUtil;
	
	@GetMapping(value = "/quan-tri/san-pham")
	public ModelAndView showList(@RequestParam("page") int page,
								@RequestParam("limit") int limit,
								HttpServletRequest request) {
		ProductDTO model = new ProductDTO();
		model.setPage(page);
		model.setLimit(limit);
		ModelAndView mav = new ModelAndView("admin/product/list");
		Pageable pageable = new PageRequest(page - 1, limit);
		model.setListResult(productService.findAll(pageable));
		model.setTotalItem(productService.getTotalItems());
		model.setTotalPage( (int) Math.ceil( (double) model.getTotalItem() / model.getLimit() ));
		if(request.getParameter("message")!=null) {
			Map<String,String> message = messageUtil.getMessage(request.getParameter("message"));
			mav.addObject("message", message.get("message"));
			mav.addObject("alert", message.get("alert"));
		}
		mav.addObject("model", model);
		return mav;
	}
	@GetMapping(value = "/quan-tri/san-pham/chinh-sua")
	public ModelAndView editProduct(@RequestParam( value = "id", required = false) Long id,
									HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("admin/product/edit");
		ProductDTO model = new ProductDTO();
		if(id != null) {
			model = productService.findById(id);
		}
		if(request.getParameter("message")!=null) {
			Map<String,String> message = messageUtil.getMessage(request.getParameter("message"));
			mav.addObject("message", message.get("message"));
			mav.addObject("alert", message.get("alert"));
		}
		mav.addObject("categories", categoryService.findAll());
		mav.addObject("model", model);
		return mav;
	}
}
