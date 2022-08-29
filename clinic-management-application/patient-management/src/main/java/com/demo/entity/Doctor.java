package com.demo.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(
			name = "VISIT",
			joinColumns = @JoinColumn(name="DOCTORID"),
			inverseJoinColumns = @JoinColumn(name="PATIENTID")
			)
	private Set<Patient> appointment = new HashSet<>();
	
	
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
	
	public void appointPatient(Patient patient) {
		appointment.add(patient);
	}
	
	

}
