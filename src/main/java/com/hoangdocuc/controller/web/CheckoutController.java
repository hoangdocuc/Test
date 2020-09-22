package com.hoangdocuc.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hoangdocuc.dto.UserDTO;
import com.hoangdocuc.service.IUserService;

@Controller(value="checkoutOfWeb")
public class CheckoutController {
	
	@Autowired
	private IUserService userService;
	
	
	@GetMapping(value="/dat-hang")
	public ModelAndView showList(@RequestParam(value="username")String username) {
		
		ModelAndView mav = new ModelAndView("/web/checkout");
		UserDTO userDTO = userService.findByUsername(username);
		mav.addObject("model",userDTO);
		return mav;
	}
	
	
}
