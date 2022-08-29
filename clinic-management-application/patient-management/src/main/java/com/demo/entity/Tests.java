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
@Table(name = "TESTS")
public class Tests {

	@Id
	@Column(name = "TESTID")
	public int testId;

	@Column(name = "TESTNAME")
	public String testName;

	@JsonIgnore
	@ManyToMany
	@JoinTable(
			name = "DIAGNOSIS",
			joinColumns = @JoinColumn(name="TESTID"),
			inverseJoinColumns = @JoinColumn(name="PATIENTID")
			)
	private Set<Patient> diagonisePatient = new HashSet<>();

	public Tests() {
	}

	public Tests(int testId, String testName) {
		this.testId = testId;
		this.testName = testName;
	}

	public int getTestId() {
		return testId;
	}

	public void setTestId(int testId) {
		this.testId = testId;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public Set<Patient> getDiagonisePatient() {
		return diagonisePatient;
	}

	public void diagonisePatient(Patient patient) {
		diagonisePatient.add(patient);
	}


}
