package com.demo.rest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import com.demo.entity.Patient;
import com.demo.entity.Tests;
import com.demo.entity.Visit;
import com.demo.repo.DoctorRepository;
import com.demo.repo.PatientRepository;
import com.demo.repo.TestRepository;
import com.demo.repo.VisitRepository;

@RestController
public class PatientRestController {

	@Autowired
	PatientRepository patientRepo;

	@Autowired
	TestRepository testRepo;

	@Autowired
	VisitRepository visitRepo;

	@Autowired
	DoctorRepository doctorRepo;

	public String getCurrentDate() {
		LocalDate localdate = LocalDate.now();
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_DATE;
		return localdate.format(dateTimeFormatter);
	}

	@PostMapping(value = "/patient/registration", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> registerPatient(@RequestBody Patient patient) {
		String date = getCurrentDate();
		patient.setDate(date);
		patientRepo.save(patient);
		return ResponseEntity.ok("{\"status\":\"Patient Registered Successfully.\"}");

	}

	@GetMapping(value = "/patient/getDetails/{name}")
	public ResponseEntity<Object> getPatientDetails(@PathVariable("name") String name) {
		Optional<Patient> patient1 = patientRepo.findByFirstName(name);
		Optional<Patient> patient2 = patientRepo.findByLastName(name);

		if (patient1.isPresent()) {
			return ResponseEntity.ok(patient1.get());
		} else if (patient2.isPresent()) {
			return ResponseEntity.ok(patient2.get());
		} else {
			return ResponseEntity.ok("{\"status\":\"Patient With name not found.\"}");
		}
	}

	@GetMapping(value = "/patient/getIdDetails/{patientId}")
	public ResponseEntity<Object> getPatientDetailsById(@PathVariable("patientId") int patientId) {
		Optional<Patient> patient1 = patientRepo.findById(patientId);

		if (patient1.isPresent()) {
			return ResponseEntity.ok(patient1.get());
		} else {
			return ResponseEntity.ok("{\"status\":\"Patient With name not found.\"}");
		}
	}
	@GetMapping(value = "/patient/getvisitDate/{patientId}")
	public ResponseEntity<Object> getPatientVisitDateById(@PathVariable("patientId") int patientId) {
		Optional<Visit> patient1 = visitRepo.findById(patientId);

		if (patient1.isPresent()) {
			return ResponseEntity.ok(patient1.get());
		} else {
			return ResponseEntity.ok("{\"status\":\"Patient With id not found.\"}");
		}
	}

	@GetMapping(value = "/patient/list", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Patient> findAllPatient() {
		return patientRepo.findAll();
	}

	@PostMapping(value = "/patient/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updatePatient(@RequestBody Patient patient) {
		if (patientRepo.existsById(patient.getPatientId())) {
			patientRepo.save(patient);
			return ResponseEntity.ok("{\"status\":\"Patient Registered Successfully.\"}");
		} else {
			return ResponseEntity.ok("{\"status\":\"Patient Not Upated Successfully.\"}");
		}

	}

	@PostMapping(value = "/tests/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> registerPatient(@RequestBody Tests test) {
		testRepo.save(test);
		return ResponseEntity.ok("{\"status\":\"Tests added Successfully.\"}");

	}

	@GetMapping(value = "/tests/list", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Tests> findAllTestsList() {
		return testRepo.findAll();
	}

	@GetMapping(value = "/tests/remove/{id}")
	public ResponseEntity<Object> getTestDetailsById(@PathVariable("id") int id) {
		Optional<Tests> patient1 = testRepo.findById(id);

		if (patient1.isPresent()) {
			testRepo.deleteById(id);
			return ResponseEntity.ok("{\"status\":\"Patient With id Deleted.\"}");
		} else {
			return ResponseEntity.ok("{\"status\":\"Patient With id not found.\"}");
		}
	}

	@GetMapping(value = "/patient/test/{patientId}/{testId}")
	public Tests addPatientTests(@PathVariable("patientId") int patientId, @PathVariable("testId") int testId) {
		Tests test = testRepo.findById(testId).get();
		Patient patient = patientRepo.findById(patientId).get();
		test.diagonisePatient(patient);
		return testRepo.save(test);
	}
}