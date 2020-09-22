package com.hoangdocuc.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hoangdocuc.converter.BillConverter;
import com.hoangdocuc.dto.BillDTO;
import com.hoangdocuc.entity.BillEntity;
import com.hoangdocuc.entity.UserEntity;
import com.hoangdocuc.repository.BillRepository;
import com.hoangdocuc.repository.UserRepository;
import com.hoangdocuc.service.IBillService;

@Service
public class BillService implements IBillService{
	
	@Autowired
	private BillRepository billRepository;
	
	@Autowired
	private BillConverter billConverter;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public List<BillDTO> findAll(Pageable pageable) {
		List<BillDTO> billDTOs = new ArrayList<>();
		List<BillEntity> billEntities = billRepository.findAll(pageable).getContent();
		for (BillEntity billEntity : billEntities) {
			BillDTO billDTO = billConverter.toDTO(billEntity);
			billDTOs.add(billDTO);
		}
		return billDTOs;
	}

	@Override
	public int getTotalItems() {
		return (int) billRepository.count();
	}

	@Override
	public BillDTO findById(long id) {
		BillEntity billEntity = billRepository.findOne(id);
		return billConverter.toDTO(billEntity);
	}

	@Override
	@Transactional
	public BillDTO save(BillDTO billDTO) {
		UserEntity user = userRepository.findOne(billDTO.getUserId());
		BillEntity newBill = new BillEntity();
		if(billDTO.getId()!=null) {
			BillEntity oldBill = billRepository.findOne(billDTO.getId());
			oldBill.setUser(user);
			newBill = billConverter.toEntity(oldBill,billDTO);
		} else {
			newBill = billConverter.toEntity(billDTO);
			newBill.setUser(user);
		}
		return billConverter.toDTO(billRepository.save(newBill));
	}

	@Override
	@Transactional
	public void delete(long[] ids) {
		for (long id : ids) {
			billRepository.delete(id);
		}
	}
	
}
