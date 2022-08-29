package com.demo.entity;


public class Doctor {

	int doctorId;

	String doctorName;

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
