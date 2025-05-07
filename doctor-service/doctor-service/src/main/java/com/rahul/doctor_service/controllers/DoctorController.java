package com.rahul.doctor_service.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rahul.doctor_service.model.DoctorModel;
import com.rahul.doctor_service.services.DoctorService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*") // Enable CORS for frontend
public class DoctorController {

	@Autowired
	private DoctorService doctorService;
	
	@PostMapping("/add-doctor")
	 public DoctorModel addDoctor(@RequestBody DoctorModel doctorModel) {
        return doctorService.addDoctor(doctorModel);
    }
	
	 @GetMapping("/list-doctor-with-filter")
	    public Page<DoctorModel> listDoctorsWithFilters(
	            @RequestParam Map<String, String> filters,
	            @RequestParam(defaultValue = "0") int page,
	            @RequestParam(defaultValue = "10") int size
	    ) {
	        Pageable pageable = PageRequest.of(page, size);
	        return doctorService.getDoctorsWithFilters(filters, pageable);
	    }
	
}
