package com.demo.entity;


public class Speciality {

	int specialityId;

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
