package com.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="DOCTOR")
public class Doctor {
	
	@Id
	@Column(name="DOCTORID")
	int doctorId;
	
	@Column(name="DOCTORNAME")
	String doctorName;
	
	@Column(name="SPECIALITY")
	String speciality;
	
	
	public Doctor() {
	}
	
	

	public Doctor(int doctorId, String doctorName, String speciality) {
		this.doctorId = doctorId;
		this.doctorName = doctorName;
		this.speciality = speciality;
	}



	public int getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}
	
	

}
