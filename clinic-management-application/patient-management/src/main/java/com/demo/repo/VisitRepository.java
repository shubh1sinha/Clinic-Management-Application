package com.demo.repo;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.demo.entity.Patient;
import com.demo.entity.Visit;

public interface VisitRepository extends JpaRepository<Visit, Integer> {
	
////		@Query("select v from Visit v inner join Patient p where v.date between?1 and ?2 and v.doctorId=?3")
//		@Query("select v from Visit v inner join Patient p  on v.patientId = p.patientId where v.doctorId=?1")
//		public List<Patient> findVisitDetails(int doctorId);
}
