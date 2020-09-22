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

import com.hoangdocuc.dto.CategoryDTO;
import com.hoangdocuc.service.ICategoryService;
import com.hoangdocuc.util.MessageUtil;

@Controller(value = "categoryControllerOfAdmin")
public class CategoryController {

	@Autowired
	private ICategoryService categoryService;
	
	@Autowired
	private MessageUtil messageUtil;

	@GetMapping(value = "/quan-tri/danh-muc")
	public ModelAndView showList(@RequestParam("page") int page,
								@RequestParam("limit") int limit,
								HttpServletRequest request) {
		CategoryDTO model = new CategoryDTO();
		model.setPage(page);
		model.setLimit(limit);
		ModelAndView mav = new ModelAndView("admin/category/list");
		Pageable pageable = new PageRequest(page - 1, limit);
		model.setListResult(categoryService.findAll(pageable));
		model.setTotalItem(categoryService.getTotalItems());
		model.setTotalPage( (int) Math.ceil( (double) model.getTotalItem() / model.getLimit() ) );
		if(request.getParameter("message") != null ) {
			Map<String,String> message = messageUtil.getMessage(request.getParameter("message"));
			mav.addObject("message", message.get("message"));
			mav.addObject("alert", message.get("alert"));
		}
		mav.addObject("model", model);
		return mav;
	}
	@GetMapping(value = "/quan-tri/danh-muc/chinh-sua")
	public ModelAndView editCategory(@RequestParam (value = "id",required = false) Long id,
									HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("admin/category/edit");
		CategoryDTO model = new CategoryDTO();
		if(id != null) {
			model = categoryService.findById(id);
		}
		if(request.getParameter("message")!=null) {
			Map<String, String> message = messageUtil.getMessage(request.getParameter("message"));
			mav.addObject("message",message.get("message"));
			mav.addObject("alert",message.get("alert"));
		}
		mav.addObject("model", model);
		return mav;
	}

}
