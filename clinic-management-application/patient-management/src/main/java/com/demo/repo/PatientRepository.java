package com.demo.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.demo.entity.Patient;
import com.demo.entity.Tests;


public interface PatientRepository  extends JpaRepository<Patient, Integer> {

	Optional<Patient> findByFirstName(String name);
	Optional<Patient> findByLastName(String name);
	@Query("select p from Patient p inner join Visit v  on p.patientId = p.patientId where p.patientId=?1")
	public List<Patient> findVisitDetails(int doctorId);

}
