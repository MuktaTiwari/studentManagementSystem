package com.pinnacle.student.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Long id;
	
	@Column
    private String name;
	
	@Column
    private String college;
	
	@Column
    private String address;
	
	@Column
    private String contactNo;
	
	@Column
    private String parentContactNo;
	
	@Column
    private String course;
	
	@Column
    private Double fees;
	
	@Column
    private Double paidFees;
	
	@Column
    private Double balanceFees;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCollege() {
		return college;
	}
	public void setCollege(String college) {
		this.college = college;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public String getParentContactNo() {
		return parentContactNo;
	}
	public void setParentContactNo(String parentContactNo) {
		this.parentContactNo = parentContactNo;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public Double getFees() {
		return fees;
	}
	public void setFees(Double fees) {
		this.fees = fees;
	}
	public Double getPaidFees() {
		return paidFees;
	}
	public void setPaidFees(Double paidFees) {
		this.paidFees = paidFees;
	}
	public Double getBalanceFees() {
		return balanceFees;
	}
	public void setBalanceFees(Double balanceFees) {
		this.balanceFees = balanceFees;
	}
    
    
    

}
