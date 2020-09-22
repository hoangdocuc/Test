package com.hoangdocuc.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="bill")
public class BillEntity extends BaseEntity{
	
	@Column(name="note")
	private String note;
	
	@Column(name="status")
	private String status;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;
	
	@OneToMany(mappedBy = "bill")
	private List<BillDetailEntity> billdetails = new ArrayList<>();

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public List<BillDetailEntity> getBilldetails() {
		return billdetails;
	}

	public void setBilldetails(List<BillDetailEntity> billdetails) {
		this.billdetails = billdetails;
	}
	
	

}
