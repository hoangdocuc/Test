package com.hoangdocuc.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.hoangdocuc.service.IBillDetailService;

@Controller(value = "billDetailOfAdmin")
public class BillDetailController {
	
	@Autowired
	private IBillDetailService billDetailService;
	
	
	

}
