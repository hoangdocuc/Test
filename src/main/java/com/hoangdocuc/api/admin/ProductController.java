package com.hoangdocuc.api.admin;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hoangdocuc.dto.ProductDTO;
import com.hoangdocuc.service.IProductService;
import com.hoangdocuc.util.UploadFileUtils;

@RestController(value = "productAPIOfAdmin")
public class ProductController {
	
	@Autowired
	private IProductService iProductService;
	
	@Autowired
	private UploadFileUtils uploadFileUtils;
	
	@PostMapping(value = "/api/san-pham")
	public ProductDTO createProduct(@RequestBody ProductDTO productDTO)  {
		byte[] decodeBase64 = Base64.getDecoder().decode(productDTO.getBase64().getBytes());
		uploadFileUtils.writeOrUpdate(decodeBase64,productDTO.getImage());
		return iProductService.save(productDTO);
	}
	
	@PutMapping(value = "/api/san-pham")
	public ProductDTO updateProduct(@RequestBody ProductDTO productDTO) {
		byte[] decodeBase64 = Base64.getDecoder().decode(productDTO.getBase64().getBytes());
		uploadFileUtils.writeOrUpdate(decodeBase64,productDTO.getImage());
		return iProductService.save(productDTO);
	}
	
	@DeleteMapping(value = "/api/san-pham")
	public void deleteProduct(@RequestBody long[] ids) {
		iProductService.delete(ids);
	}
	
	
}
