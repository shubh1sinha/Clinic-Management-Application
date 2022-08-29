package com.demo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.demo.entity.Doctor;
import com.demo.entity.Patient;
import com.demo.entity.Speciality;
import com.demo.entity.Tests;
import com.demo.entity.Visit;


@Controller
public class ClinicController {
	
	@Autowired
	RestTemplate rt;
	
	@GetMapping(value = "/clinic/patient")
	public String getPatientRegistrationPage(ModelMap map) {
		Patient patient = new Patient();
		map.addAttribute("patient", patient);
		return "registration";
	}
	
	@PostMapping(value = "/clinic/registration")
	public ModelAndView patientRegistration(@ModelAttribute("patient") Patient patient) {
		ModelAndView mv = new ModelAndView();

		ResponseEntity<String> response = rt.postForEntity("http://localhost:8180/patient/registration", patient, String.class);

		mv.addObject(response);
		String message = "Patient Registered Scuessfully! Please Login";
		mv.addObject("message", message);
		mv.setViewName("sucess");
		return mv;

	}
	
	
	@GetMapping(value = "/clinic/find/patient")
	public String getPatientDetailsPage(ModelMap map) {
		return "patientDetails";
	}
	
	@PostMapping(value = "/clinic/find/getPatient")
	public ModelAndView patientDetails(@RequestParam("name") String name) {
		ModelAndView mv = new ModelAndView();

		ResponseEntity<Patient> response = rt.getForEntity("http://localhost:8180/patient/getDetails/"+name, Patient.class);
		mv.addObject("patientDetails", response.getBody());
		mv.setViewName("patientDetails");
		return mv;
	}
	
	@GetMapping(value = "/clinic/find/id")
	public String getPatientDetailsIdPage(ModelMap map) {
		return "patientIdDetails";
	}
	
	@PostMapping(value = "/clinic/find/getId")
	public ModelAndView patientDetailsById(@RequestParam("patientId") int patientId) {
		ModelAndView mv = new ModelAndView();
		ResponseEntity<Patient> response = rt.getForEntity("http://localhost:8180/patient/getIdDetails/"+patientId, Patient.class);
		ResponseEntity<Visit> response1 = rt.getForEntity("http://localhost:8180/patient/getvisitDate/"+patientId, Visit.class);
		mv.addObject("patientIdDetails", response.getBody());
		mv.addObject("dateDetails", response1.getBody());
		mv.setViewName("patientIdDetails");
		return mv;
	}
	
	@GetMapping(value = "/clinic/patientList")
	public ModelAndView getCustomerList() {
		ModelAndView mv = new ModelAndView();
		ResponseEntity<List<Patient>> response = rt.exchange("http://localhost:8180/patient/list", HttpMethod.GET,
				null, new ParameterizedTypeReference<List<Patient>>() {
				});
		mv.setViewName("patientList");
		mv.addObject("patientList", response.getBody());
		return mv;
	}
	
	@GetMapping(value = "/clinic/patient/update/{id}")
	public ModelAndView updatePatientDetailsPage(@PathVariable("id") int id,ModelMap map) {
		ModelAndView mv = new ModelAndView();
		ResponseEntity<Patient> response = rt.getForEntity("http://localhost:8180/patient/getIdDetails/"+id, Patient.class);
		mv.addObject("patientIdDetails", response.getBody());
		mv.setViewName("updatePatient");
		return mv;
	}
	
	@PostMapping(value = "/clinic/patient/update/updated")
	public ModelAndView patientUpdation(@ModelAttribute("patient") Patient patient) {
		ModelAndView mv = new ModelAndView();

		ResponseEntity<String> response = rt.postForEntity("http://localhost:8180/patient/update", patient, String.class);

		mv.addObject(response);
		String message = "Patient Registered Scuessfully! Please Login";
		mv.addObject("message", message);
		mv.setViewName("sucess");
		return mv;

	}
	
	@GetMapping(value = "/clinic/testsList")
	public ModelAndView getTestsList() {
		ModelAndView mv = new ModelAndView();
		ResponseEntity<List<Tests>> response = rt.exchange("http://localhost:8180/tests/list", HttpMethod.GET,
				null, new ParameterizedTypeReference<List<Tests>>() {
				});
		mv.setViewName("testsList");
		mv.addObject("testsList", response.getBody());
		return mv;
	}
	
	@GetMapping(value = "/clinic/test/add")
	public String getAddTestPage(ModelMap map) {
		Tests tests = new Tests();
		map.addAttribute("tests", tests);
		return "addTest";
	}
	
	@PostMapping(value = "/clinic/test/added")
	public ModelAndView addTests(@ModelAttribute("tests") Tests tests) {
		ModelAndView mv = new ModelAndView();

		ResponseEntity<String> response = rt.postForEntity("http://localhost:8180/tests/add", tests, String.class);

		mv.addObject(response);
		String message = "Test added Scuessfully! ";
		mv.addObject("message", message);
		mv.setViewName("sucess");
		return mv;

	}
	
	@GetMapping(value = "/clinic/test/remove/{id}")
	public ModelAndView removeTests(@PathVariable("id") int id, ModelMap map) {
		ModelAndView mv = new ModelAndView();

		ResponseEntity<String> response = rt.getForEntity("http://localhost:8180/tests/remove/"+id, String.class);

		mv.addObject(response);
		String message = "Test removed Scuessfully! ";
		mv.addObject("message", message);
		mv.setViewName("sucess");
		return mv;

	}
	
	@GetMapping(value = "/clinic/patient/test")
	public ModelAndView getAddPatientTestPage(ModelMap map) {
		ModelAndView mv = new ModelAndView();
		ResponseEntity<List<Tests>> test = rt.exchange("http://localhost:8180/tests/list", HttpMethod.GET,
				null, new ParameterizedTypeReference<List<Tests>>() {
				});
		ResponseEntity<List<Patient>> patient = rt.exchange("http://localhost:8180/patient/list", HttpMethod.GET,
				null, new ParameterizedTypeReference<List<Patient>>() {
				});
		mv.setViewName("addPatientTest");
		mv.addObject("testsList", test.getBody());
		mv.addObject("patientsList", patient.getBody());
		return mv;
	}
	

	
	@PostMapping(value = "/clinic/patient/added")
	public ModelAndView AddPatientTests(@RequestParam("testId") int testId, @RequestParam("patientId") int patientId, ModelMap map) {
		ModelAndView mv = new ModelAndView();

		ResponseEntity<String> response = rt.getForEntity("http://localhost:8180/patient/test/"+patientId+"/"+testId, String.class);

		mv.addObject(response);
		String message = "Test Added Scuessfully! ";
		mv.addObject("message", message);
		mv.setViewName("sucess");
		return mv;

	}
	
	@GetMapping(value = "/clinic/test/patient/{id}")
	public ModelAndView getPatienttestsDetailsPage(@PathVariable("id") int id,ModelMap map) {
		ModelAndView mv = new ModelAndView();
		ResponseEntity<Patient> response = rt.getForEntity("http://localhost:8180/patient/getIdDetails/"+id, Patient.class);
		
		mv.addObject("test", response.getBody());
		mv.setViewName("testPatient");
		return mv;
	}
	
	@GetMapping(value = "/clinic/patient/test/list")
	public ModelAndView getCustomerTestList() {
		ModelAndView mv = new ModelAndView();
		ResponseEntity<List<Patient>> response = rt.exchange("http://localhost:8180/patient/list", HttpMethod.GET,
				null, new ParameterizedTypeReference<List<Patient>>() {
				});
		mv.setViewName("diagnosisList");
		mv.addObject("patientList", response.getBody());
		return mv;
	}
	
	@GetMapping(value = "/clinic/specsList")
	public ModelAndView getSpecialityList() {
		ModelAndView mv = new ModelAndView();
		ResponseEntity<List<Speciality>> response = rt.exchange("http://localhost:8380/specs/list", HttpMethod.GET,
				null, new ParameterizedTypeReference<List<Speciality>>() {
				});
		mv.setViewName("specsList");
		mv.addObject("sepecsList", response.getBody());
		return mv;
	}
	
	@GetMapping(value = "/clinic/specs/add")
	public String getAddSpecialityPage(ModelMap map) {
		Speciality specs = new Speciality();
		map.addAttribute("specs", specs);
		return "addSpecs";
	}
	
	@PostMapping(value = "/clinic/specs/added")
	public ModelAndView addSpeciality(@ModelAttribute("specs") Speciality specs) {
		ModelAndView mv = new ModelAndView();

		ResponseEntity<String> response = rt.postForEntity("http://localhost:8380/specs/add", specs, String.class);

		mv.addObject(response);
		String message = "Specuality added Scuessfully! ";
		mv.addObject("message", message);
		mv.setViewName("sucess");
		return mv;

	}
	
	@GetMapping(value = "/clinic/doctor/add/{id}")
	public ModelAndView addDoctorToSpecs(@PathVariable("id") int id,ModelMap map) {
		ModelAndView mv = new ModelAndView();
		ResponseEntity<Speciality> response = rt.getForEntity("http://localhost:8380/specs/get/"+id, Speciality.class);
		mv.addObject("specs", response.getBody());
		mv.setViewName("addDoctor");
		return mv;
	}
	
	@PostMapping(value = "/clinic/doctor/add/added")
	public ModelAndView doctorAddedToSepcs(@ModelAttribute("doctor") Doctor doctor) {
		ModelAndView mv = new ModelAndView();

		ResponseEntity<String> response = rt.postForEntity("http://localhost:8380/doctor/add", doctor, String.class);

		mv.addObject(response);
		String message = "Doctor Added Scuessfully! Please Login";
		mv.addObject("message", message);
		mv.setViewName("sucess");
		return mv;

	}
	

	
	@GetMapping(value = "/clinic/doctor/view/{specs}")
	public ModelAndView getDoctorSpecsLsit(@PathVariable("specs")String specs, ModelMap map) {
		ModelAndView mv = new ModelAndView();
		ResponseEntity<List<Doctor>> response = rt.exchange("http://localhost:8380/doctor/get/" + specs,
				HttpMethod.GET, null, new ParameterizedTypeReference<List<Doctor>>() {
				});
		mv.setViewName("doctorList");
		mv.addObject("doctorList", response.getBody());
		return mv;
	}
	
	@GetMapping(value = "/clinic/doctor/remove/{id}")
	public ModelAndView removeDoctor(@PathVariable("id") int id, ModelMap map) {
		ModelAndView mv = new ModelAndView();

		ResponseEntity<String> response = rt.getForEntity("http://localhost:8380/doctor/remove/"+id, String.class);

		mv.addObject(response);
		String message = "Doctor removed Scuessfully! ";
		mv.addObject("message", message);
		mv.setViewName("sucess");
		return mv;

	}
	
	@GetMapping(value = "/clinic/doctorList")
	public ModelAndView getDoctorList() {
		ModelAndView mv = new ModelAndView();
		ResponseEntity<List<Doctor>> response = rt.exchange("http://localhost:8380/doctor/list", HttpMethod.GET,
				null, new ParameterizedTypeReference<List<Doctor>>() {
				});
		mv.setViewName("allDoctors");
		mv.addObject("doctorList", response.getBody());
		return mv;
	}
	
	
	@GetMapping(value = "/clinic/patient/visit")
	public ModelAndView getAddVisit(ModelMap map) {
		ModelAndView mv = new ModelAndView();
		ResponseEntity<List<Doctor>> doctor = rt.exchange("http://localhost:8380/doctor/list", HttpMethod.GET,
				null, new ParameterizedTypeReference<List<Doctor>>() {
				});
		ResponseEntity<List<Patient>> patient = rt.exchange("http://localhost:8180/patient/list", HttpMethod.GET,
				null, new ParameterizedTypeReference<List<Patient>>() {
				});
		mv.setViewName("addPatientVisit");
		mv.addObject("doctorsList", doctor.getBody());
		mv.addObject("patientsList", patient.getBody());
		return mv;
	}
	
	@PostMapping(value = "/clinic/patient/listed")
	public ModelAndView AddPatientVisits( @RequestParam("patientId") int patientId,  @RequestParam("doctorId") int doctorId, ModelMap map) {
		ModelAndView mv = new ModelAndView();

		ResponseEntity<String> response = rt.getForEntity("http://localhost:8180/patient/visit/"+patientId+"/"+doctorId, String.class);

		mv.addObject(response);
		String message = "Visit Added Scuessfully! ";
		mv.addObject("message", message);
		mv.setViewName("sucess");
		return mv;

	}
	
	@GetMapping(value = "/clinic/patient/appointment")
	public ModelAndView getAppointmentList(@RequestParam("patientId") int patientId) {
		ModelAndView mv = new ModelAndView();
		ResponseEntity<List<Patient>> response = rt.exchange("http://localhost:8180/patient/getIdDetails/"+patientId, HttpMethod.GET,
				null, new ParameterizedTypeReference<List<Patient>>() {
				});
		mv.setViewName("appointment");
		mv.addObject("visitList", response.getBody());
		return mv;
	}


}
