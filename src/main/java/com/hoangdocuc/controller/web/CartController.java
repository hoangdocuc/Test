package com.hoangdocuc.controller.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.hoangdocuc.dto.BillDetailDTO;
import com.hoangdocuc.dto.CategoryDTO;
import com.hoangdocuc.dto.ProductDTO;
import com.hoangdocuc.service.ICategoryService;
import com.hoangdocuc.service.IProductService;

@Controller(value = "cartControllerOfWeb")
@SessionAttributes("cart")
public class CartController {
	
	@Autowired
	private IProductService productService;
	
	@Autowired
	private ICategoryService categoryService;
	
	
	
	@ModelAttribute("cart")
	public List<BillDetailDTO> list(){
		List<BillDetailDTO>  list =  new ArrayList<>();
		return list;
	}
	
	@GetMapping(value="/gio-hang")
	public ModelAndView showList(@ModelAttribute("cart")List<BillDetailDTO> list ) {
		ModelAndView mav = new ModelAndView("web/cart");
		CategoryDTO model3 = new CategoryDTO();
		model3.setListResult(categoryService.find4All());
		mav.addObject("modelCategory4", model3);
		ProductDTO model2 = new ProductDTO();
		model2.setListResult(productService.find8ByCreatedDate());
		mav.addObject("modelbyCreatedDate", model2);
		mav.addObject("model", list );
		Integer total = 0;
		for (BillDetailDTO BillDetailDTO : list) {
			total +=BillDetailDTO.getProductPrice()*BillDetailDTO.getQuantity();
		}
		mav.addObject("total",total );
		return mav;
	}
	@GetMapping(value="/gio-hang/them")
	public String addCart(HttpServletRequest request,Model model,
						@RequestParam(value = "id") Long id,
						@ModelAttribute("cart") List<BillDetailDTO> list) {		
		ProductDTO productDTO = new ProductDTO();
		productDTO = productService.findById(id);
		BillDetailDTO billDetailDTO = new BillDetailDTO();
		billDetailDTO.setProductId(id);
		billDetailDTO.setQuantity(1);
		billDetailDTO.setProductImage(productDTO.getImage());
		billDetailDTO.setProductName(productDTO.getName());
		billDetailDTO.setProductPrice(productDTO.getPrice());
		billDetailDTO.setProductQuantity(productDTO.getQuantity());
		boolean check = true;
		for (BillDetailDTO bill : list) {
			if(bill.getProductId()==billDetailDTO.getProductId()) {
				bill.setQuantity(bill.getQuantity()+1);
				check = false;
			}
		}
		if(check) {
			list.add(billDetailDTO);
			model.addAttribute("cart", list);
		}
		return "redirect:"+request.getHeader("Referer");
	}
	
	@GetMapping(value="/gio-hang/sua")
	public String editCart(@RequestParam("productId")Long productId,
						@RequestParam("quantity")Integer quantity,
						@ModelAttribute("cart") List<BillDetailDTO> list)  {
		for (BillDetailDTO bill : list) {
			if(bill.getProductId()==productId) {
				bill.setQuantity(quantity);
			}
		}
		return "redirect:/gio-hang";
	}
	
	@GetMapping(value="/gio-hang/xoa")
	public String deleteCart(HttpServletRequest request,Model model,
						@RequestParam("productId")Long productId,
						@ModelAttribute("cart") List<BillDetailDTO> list)  {
		BillDetailDTO billindex = new BillDetailDTO();
		for (BillDetailDTO bill : list) {
			if(bill.getProductId()==productId) {
				billindex = bill;
			}
		}
		list.remove(billindex);
		return "redirect:/gio-hang";
	}

}
