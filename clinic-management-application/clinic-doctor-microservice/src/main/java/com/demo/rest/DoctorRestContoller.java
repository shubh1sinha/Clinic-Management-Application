package com.demo.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.entity.Doctor;
import com.demo.entity.Speciality;
import com.demo.repo.DoctorRepository;
import com.demo.repo.SpecialityRepository;

@RestController
public class DoctorRestContoller {
	
	@Autowired
	DoctorRepository doctorRepo;
	
	@Autowired
	SpecialityRepository specsRepo;
	
	@GetMapping(value = "/specs/list", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Speciality> findAllTestsList() {
		return specsRepo.findAll();
	}
	
	@PostMapping(value = "/specs/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> registerPatient(@RequestBody Speciality specs)
	{
		specsRepo.save(specs);
		return ResponseEntity.ok("{\"status\":\"Speciality added Successfully.\"}");
		
	}
	
	@GetMapping(value = "/specs/get/{id}")
	public ResponseEntity<Object> getPatientDetailsById(@PathVariable("id") int id) {
		Optional<Speciality> specs = specsRepo.findById(id);

		if (specs.isPresent()) {
			return ResponseEntity.ok(specs.get());
		} else {
			return ResponseEntity.ok("{\"status\":\"Specs With name not found.\"}");
		}
	}
	
	@PostMapping(value = "/doctor/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addDoctor(@RequestBody Doctor doctor)
	{
		doctorRepo.save(doctor);
		return ResponseEntity.ok("{\"status\":\"Doctor saved Successfully.\"}");
		
	}
	
	@GetMapping(value = "/doctor/get/{specs}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Doctor> findAllDoctorsListBySpecs(@PathVariable("specs") String specs) {
		return doctorRepo.findAllBySpeciality(specs);
	}
	
	@GetMapping(value = "/doctor/remove/{id}")
	public ResponseEntity<Object> getTestDetailsById(@PathVariable("id") int id)
	{
		Optional<Doctor> doctor = doctorRepo.findById(id);
		
		if(doctor.isPresent()) {
			doctorRepo.deleteById(id);
			return ResponseEntity.ok("{\"status\":\"Doctor With id Deleted.\"}");
		}
		else
		{
		return ResponseEntity.ok("{\"status\":\"Doctor With id not found.\"}");
		}
	}
	
	@GetMapping(value = "/doctor/list", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Doctor> findAllDoctorList() {
		return doctorRepo.findAll();
	}
	

}
