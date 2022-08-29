package com.demo.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "PATIENT")
public class Patient {
	
	@Id
	@Column(name = "PATIENTID")
	public int patientId;
	
	@Column(name = "FIRSTNAME")
	public String firstName;
	
	@Column(name = "LASTNAME")
	public String lastName;
	
	@Column(name = "CONTACT")
	public double contact;
	
	@Column(name = "CITY")
	public String city;
	
	@Column(name = "DATE")
	public String date;
	
	
	@ManyToMany(mappedBy = "diagonisePatient")
	private Set<Tests> tests = new HashSet<>();
	
	@ManyToMany(mappedBy = "appointment")
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
