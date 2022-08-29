package com.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.entity.Speciality;

public interface SpecialityRepository extends JpaRepository<Speciality, Integer> {

}
