package com.hoangdocuc.api.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hoangdocuc.service.IBillDetailService;

@RestController(value= "billDetailAPIOfAdmin")
public class BillDetailController {
	
	@Autowired
	private IBillDetailService iBillDetailService;
	
	@DeleteMapping(value = "/api/chi-tiet-hoa-don")
	public void deleteBillDetail(@RequestBody long[] ids) {
		iBillDetailService.delete(ids);
	}
	

}
