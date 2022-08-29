package com.demo.entity;

import java.util.HashSet;
import java.util.Set;

public class Tests {

	public int testId;

	public String testName;

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

	public void setDiagonisepatient(Set<Patient> diagonisePatient) {
		this.diagonisePatient = diagonisePatient;
	}

	public void diagonisePatient(Patient patient) {
		diagonisePatient.add(patient);
	}

	

}
