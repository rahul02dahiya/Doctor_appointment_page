package com.rahul.doctor_service.services;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.rahul.doctor_service.model.DoctorModel;

public interface DoctorService {
	 DoctorModel addDoctor(DoctorModel doctorModel);
	 Page<DoctorModel> getDoctorsWithFilters(Map<String, String> filters, Pageable pageable);
}
