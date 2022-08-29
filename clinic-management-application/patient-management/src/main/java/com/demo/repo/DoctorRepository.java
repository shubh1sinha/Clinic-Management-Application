package com.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.entity.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

	List<Doctor> findAllBySpeciality(String specs);

}
