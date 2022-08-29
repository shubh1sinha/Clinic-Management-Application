package com.demo.entity;

import java.util.HashSet;
import java.util.Set;

public class Patient {

	public int patientId;

	public String firstName;

	public String lastName;

	public double contact;

	public String city;

	public String date;

	private Set<Tests> tests = new HashSet<>();

	private Set<Doctor> appintments = new HashSet<>();



	public Patient() {
	}

	public Patient(int patientId, String firstName, String lastName, double contact, String city, String date) {
		this.patientId = patientId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.contact = contact;
		this.city = city;
		this.date = date;
	}

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public double getContact() {
		return contact;
	}

	public void setContact(double contact) {
		this.contact = contact;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	public Set<Tests> getTests() {
		return tests;
	}
	
	public Set<Doctor> getAppintments() {
		return appintments;
	}

}
