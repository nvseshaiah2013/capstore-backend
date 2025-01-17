package com.cg.capstore.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="INVITES")
public class Invitation implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name="HEADER")
	private String header;
	
	@Column(name="MESSAGE")
	private String message;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="USERNAME")
	private MerchantDetails merchant;
	
	@Column(name="IN_DATE")
	private Timestamp invitedDate;
	
	@Column(name="ACCEPTED")
	private int isAccepted;
	
	public  Invitation () {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public MerchantDetails getMerchant() {
		return merchant;
	}

	public void setMerchant(MerchantDetails merchant) {
		this.merchant = merchant;
	}

	public Timestamp getDate() {
		return invitedDate;
	}

	public void setDate(Timestamp date) {
		this.invitedDate = date;
	}

	public int getIsAccepted() {
		return isAccepted;
	}

	public void setIsAccepted(int isAccepted) {
		this.isAccepted = isAccepted;
	}
	
	
}
