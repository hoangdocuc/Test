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

import com.hoangdocuc.dto.BillDTO;
import com.hoangdocuc.dto.BillDetailDTO;
import com.hoangdocuc.repository.BillRepository;
import com.hoangdocuc.service.IBillDetailService;
import com.hoangdocuc.service.IBillService;
import com.hoangdocuc.util.MessageUtil;

@Controller(value = "billControllerofAdmin")
public class BillController {
	
	@Autowired
	private IBillService iBillService;
	
	@Autowired
	private IBillDetailService iBillDetailService;
	
	@Autowired
	private BillRepository billRepository;
	
	@Autowired
	private MessageUtil messageUtil;
	
	@GetMapping(value="/quan-tri/hoa-don")
	public ModelAndView showList(@RequestParam("page") int page,
								@RequestParam("limit") int limit,
								HttpServletRequest request) {
		BillDTO model = new BillDTO();
		model.setPage(page);
		model.setLimit(limit);
		ModelAndView mav = new ModelAndView("admin/bill/list");
		Pageable pageable = new PageRequest(page-1,limit);
		model.setListResult(iBillService.findAll(pageable));
		model.setTotalItem(iBillService.getTotalItems());
		model.setTotalPage((int) Math.ceil( (double) model.getTotalItem() / model.getLimit() ));
		if(request.getParameter("message")!=null) {
			Map<String,String> message = messageUtil.getMessage(request.getParameter("message"));
			mav.addObject("message", message.get("message"));
			mav.addObject("alert", message.get("alert"));
		}
		mav.addObject("model", model);
		return mav;
	}
	@GetMapping(value = "/quan-tri/hoa-don/chinh-sua")
	public ModelAndView editBill(@RequestParam(value="id",required = false)Long id,
								HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("admin/bill/edit");
		BillDTO model = new BillDTO();
		if(id!=null) {
			model = iBillService.findById(id);
			BillDetailDTO billDetailDTO = new BillDetailDTO();
			billDetailDTO.setListResult(iBillDetailService.findByBill(billRepository.findOne(id)));
			mav.addObject("modelBillDetail", billDetailDTO);
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
