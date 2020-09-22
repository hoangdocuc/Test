package com.hoangdocuc.api.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hoangdocuc.dto.BillDTO;
import com.hoangdocuc.service.IBillService;

@RestController(value= "billAPIOfAdmin")
public class BillController {
	
	@Autowired
	private IBillService iBillService;
	
	@PostMapping(value = "/api/hoa-don")
	public BillDTO createBill(@RequestBody BillDTO billDTO) {
		return iBillService.save(billDTO);
	}
	
	@PutMapping(value="/api/hoa-don")
	public BillDTO updateBill(@RequestBody BillDTO billDTO) {
		return iBillService.save(billDTO);
	}
	
	@DeleteMapping(value="/api/hoa-don")
	public void deleteBill(@RequestBody long[] ids) {
		iBillService.delete(ids);
	}
	

}
