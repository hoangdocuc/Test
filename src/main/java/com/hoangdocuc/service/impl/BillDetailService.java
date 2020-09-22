package com.hoangdocuc.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hoangdocuc.converter.BillDetailConverter;
import com.hoangdocuc.dto.BillDetailDTO;
import com.hoangdocuc.entity.BillDetailEntity;
import com.hoangdocuc.entity.BillEntity;
import com.hoangdocuc.entity.ProductEntity;
import com.hoangdocuc.repository.BillDetailRepository;
import com.hoangdocuc.repository.BillRepository;
import com.hoangdocuc.repository.ProductRepository;
import com.hoangdocuc.service.IBillDetailService;

@Service
public class BillDetailService implements IBillDetailService{
	
	@Autowired
	private BillDetailRepository billDetailRepository;
	
	@Autowired
	private BillDetailConverter billDetailConverter;
	
	@Autowired
	private BillRepository billRepository;
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<BillDetailDTO> findAll(Pageable pageable) {
		List<BillDetailDTO> billDetailDTOs = new ArrayList<>();
		List<BillDetailEntity> billDetailEntities = billDetailRepository.findAll(pageable).getContent();
		for (BillDetailEntity billDetailEntity : billDetailEntities) {
			BillDetailDTO billDetailDTO = billDetailConverter.toDTO(billDetailEntity);
			billDetailDTOs.add(billDetailDTO);
		}
		return billDetailDTOs;
	}

	@Override
	public BillDetailDTO findById(long id) {
		BillDetailEntity billDetailEntity = billDetailRepository.findOne(id);
		return billDetailConverter.toDTO(billDetailEntity);
	}

	@Override
	@Transactional
	public BillDetailDTO save(BillDetailDTO billDetailDTO) {
		ProductEntity product = productRepository.findOne(billDetailDTO.getProductId());
		BillEntity bill = billRepository.findOne(billDetailDTO.getBillId());
		BillDetailEntity newBillDetail = new BillDetailEntity();
		if(billDetailDTO.getId() != null) {
			BillDetailEntity oldeBillDetail = billDetailRepository.findOne(billDetailDTO.getId());
			oldeBillDetail.setProduct(product);
			oldeBillDetail.setBill(bill);
		} else {
			newBillDetail = billDetailConverter.toEntity(billDetailDTO);
			newBillDetail.setProduct(product);
			newBillDetail.setBill(bill);
		}
		return billDetailConverter.toDTO(billDetailRepository.save(newBillDetail));
	}

	@Override
	@Transactional
	public void delete(long[] ids) {
		for (long id : ids) {
			billDetailRepository.delete(id);
		}
		
	}

	@Override
	public List<BillDetailDTO> findByBill(BillEntity billEntity) {
		List<BillDetailDTO> billDetailDTOs = new ArrayList<>();
		List<BillDetailEntity> listBillDetail = billDetailRepository.findByBill(billEntity);
		for (BillDetailEntity billDetailEntity : listBillDetail) {
			billDetailDTOs.add(billDetailConverter.toDTO(billDetailEntity));
		}
		return billDetailDTOs;
	}

	

	

}
