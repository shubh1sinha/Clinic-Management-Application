package com.demo.entity;

public class Visit {

	int patientId;

	int doctorId;

	String date;

	public Visit() {
	}

	public Visit(int patientId, String patientName, int doctorId, String date) {

		this.patientId = patientId;
		this.doctorId = doctorId;
		this.date = date;
	}

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public int getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
