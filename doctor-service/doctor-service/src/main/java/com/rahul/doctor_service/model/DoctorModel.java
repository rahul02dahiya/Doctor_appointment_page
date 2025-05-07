package com.rahul.doctor_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class DoctorModel {

	public DoctorModel() {
		super();
	}
	
	public DoctorModel(Long id, String name, String speciality, String gender, int experience, String location,
			double fees, String languages, String facility, boolean hospitalVisit, boolean onlineConsult,
			String profilePhotoUrl) {
		super();
		this.name = name;
		this.speciality = speciality;
		this.gender = gender;
		this.experience = experience;
		this.location = location;
		this.fees = fees;
		this.languages = languages;
		this.facility = facility;
		this.hospitalVisit = hospitalVisit;
		this.onlineConsult = onlineConsult;
		this.profilePhotoUrl = profilePhotoUrl;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String speciality;
    private String gender;
    private int experience;
    private String location;
    private double fees;
    private String languages;
    private String facility;
    private boolean hospitalVisit;
    private boolean onlineConsult;
    private String profilePhotoUrl;
    
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public double getFees() {
		return fees;
	}

	public void setFees(double fees) {
		this.fees = fees;
	}

	public String getLanguages() {
		return languages;
	}

	public void setLanguages(String languages) {
		this.languages = languages;
	}

	public String getFacility() {
		return facility;
	}

	public void setFacility(String facility) {
		this.facility = facility;
	}

	public boolean isHospitalVisit() {
		return hospitalVisit;
	}

	public void setHospitalVisit(boolean hospitalVisit) {
		this.hospitalVisit = hospitalVisit;
	}

	public boolean isOnlineConsult() {
		return onlineConsult;
	}

	public void setOnlineConsult(boolean onlineConsult) {
		this.onlineConsult = onlineConsult;
	}

	public String getProfilePhotoUrl() {
		return profilePhotoUrl;
	}

	public void setProfilePhotoUrl(String profilePhotoUrl) {
		this.profilePhotoUrl = profilePhotoUrl;
	}

	@Override
	public String toString() {
		return "DoctorModel [id=" + id + ", name=" + name + ", speciality=" + speciality + ", gender=" + gender
				+ ", experience=" + experience + ", location=" + location + ", fees=" + fees + ", languages="
				+ languages + ", facility=" + facility + ", hospitalVisit=" + hospitalVisit + ", onlineConsult="
				+ onlineConsult + ", profilePhotoUrl=" + profilePhotoUrl + "]";
	}
    
}
