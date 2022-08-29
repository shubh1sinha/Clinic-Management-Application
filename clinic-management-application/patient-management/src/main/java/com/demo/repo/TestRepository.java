package com.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.entity.Tests;

public interface TestRepository extends JpaRepository<Tests, Integer> {
	
	

}
