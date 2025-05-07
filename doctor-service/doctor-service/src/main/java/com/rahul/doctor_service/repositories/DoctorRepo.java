package com.rahul.doctor_service.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.rahul.doctor_service.model.DoctorModel;

@Repository
public interface DoctorRepo extends JpaRepository<DoctorModel, Long> {
    @Query("SELECT d FROM DoctorModel d " +
           "WHERE (:hospitalVisit IS NULL OR d.hospitalVisit = :hospitalVisit) " +
           "AND (:onlineConsult IS NULL OR d.onlineConsult = :onlineConsult) " +
           "AND (:experience IS NULL OR " +
           "   (:expMin IS NULL OR d.experience >= :expMin) AND " +
           "   (:expMax IS NULL OR d.experience <= :expMax)) " +
           "AND (:fees IS NULL OR " +
           "   (:feesMin IS NULL OR d.fees >= :feesMin) AND " +
           "   (:feesMax IS NULL OR d.fees <= :feesMax)) " +
           "AND (:language IS NULL OR d.languages LIKE %:language%) " +
           "AND (:facility IS NULL OR d.facility = :facility)")
    Page<DoctorModel> findDoctorsWithFilters(
        @Param("hospitalVisit") Boolean hospitalVisit,
        @Param("onlineConsult") Boolean onlineConsult,
        @Param("experience") String experience,
        @Param("expMin") Integer expMin,
        @Param("expMax") Integer expMax,
        @Param("fees") String fees,
        @Param("feesMin") Double feesMin,
        @Param("feesMax") Double feesMax,
        @Param("language") String language,
        @Param("facility") String facility,
        Pageable pageable
    );
}