package com.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="SPECIALITY")
public class Speciality {
	
	@Id
	@Column(name="SPECIALITYID")
	int specialityId;
	
	@Column(name="SPECIALITY")
	String speciality;

	public Speciality() {
	}

	public Speciality(int specialityId, String speciality) {
		this.specialityId = specialityId;
		this.speciality = speciality;
	}

	public int getSpecialityId() {
		return specialityId;
	}

	public void setSpecialityId(int specialityId) {
		this.specialityId = specialityId;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpecialityName(String specialityName) {
		this.speciality = specialityName;
	}
	
	

}
