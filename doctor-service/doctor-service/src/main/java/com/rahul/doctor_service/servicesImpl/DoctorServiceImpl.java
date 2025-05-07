package com.rahul.doctor_service.servicesImpl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.rahul.doctor_service.model.DoctorModel;
import com.rahul.doctor_service.repositories.DoctorRepo;
import com.rahul.doctor_service.services.DoctorService;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepo doctorRepo;

    @Override
    public DoctorModel addDoctor(DoctorModel doctorModel) {
        return doctorRepo.save(doctorModel);
    }

    @Override
    public Page<DoctorModel> getDoctorsWithFilters(Map<String, String> filters, Pageable pageable) {
        System.out.println("Filters received: " + filters);

        Boolean hospitalVisit = filters.get("hospitalVisit") != null 
            ? Boolean.parseBoolean(filters.get("hospitalVisit")) : null;
        Boolean onlineConsult = filters.get("onlineConsult") != null 
            ? Boolean.parseBoolean(filters.get("onlineConsult")) : null;
        String experience = filters.get("experience");
        String fees = filters.get("fees");
        String language = filters.get("language");
        String facility = filters.get("facility");

        // Map experience ranges
        Integer expMin = null, expMax = null;
        if (experience != null && !experience.isEmpty()) {
            String[] ranges = experience.split(",");
            for (String range : ranges) {
                switch (range.trim()) {
                    case "0-5" -> { expMin = expMin == null ? 0 : Math.min(expMin, 0); expMax = expMax == null ? 5 : Math.max(expMax, 5); }
                    case "6-10" -> { expMin = expMin == null ? 6 : Math.min(expMin, 6); expMax = expMax == null ? 10 : Math.max(expMax, 10); }
                    case "11-16" -> { expMin = expMin == null ? 11 : Math.min(expMin, 11); expMax = expMax == null ? 16 : Math.max(expMax, 16); }
                    case "16+" -> { expMin = expMin == null ? 16 : Math.min(expMin, 16); expMax = Integer.MAX_VALUE; }
                }
            }
        }

        // Map fees ranges
        Double feesMin = null, feesMax = null;
        if (fees != null && !fees.isEmpty()) {
            String[] ranges = fees.split(",");
            for (String range : ranges) {
                switch (range.trim()) {
                    case "100-500" -> { feesMin = feesMin == null ? 100.0 : Math.min(feesMin, 100.0); feesMax = feesMax == null ? 500.0 : Math.max(feesMax, 500.0); }
                    case "500-1000" -> { feesMin = feesMin == null ? 500.0 : Math.min(feesMin, 500.0); feesMax = feesMax == null ? 1000.0 : Math.max(feesMax, 1000.0); }
                    case "1000+" -> { feesMin = feesMin == null ? 1000.0 : Math.min(feesMin, 1000.0); feesMax = Double.MAX_VALUE; }
                }
            }
        }

        // Handle language
        String formattedLanguage = language != null && !language.isEmpty() ? language : null;

        // Apply sorting
        String sortField = filters.get("sort");
        String order = filters.get("order");
        Pageable sortedPageable = pageable;
        if (sortField != null && order != null) {
            Sort sort = Sort.by(order.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, sortField);
            sortedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
        }

        return doctorRepo.findDoctorsWithFilters(
            hospitalVisit, onlineConsult, experience, expMin, expMax,
            fees, feesMin, feesMax, formattedLanguage, facility, sortedPageable
        );
    }
}