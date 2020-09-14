package com.Project.MoveInMed.GestionPro.repository;

import com.Project.MoveInMed.GestionPro.enumClass.ParamChoice;
import com.Project.MoveInMed.GestionPro.entity.Professional;


import java.util.List;

/**
 * Class parent of all repository's class
 */
public interface ProfessionalRepository {
    Professional create(Professional professional);
    Professional update(Professional professional, Long id);
    void delete(Long id);
    List<Professional> sort(ParamChoice.professionalSortChoice choice, String  name , ParamChoice.professionalJobs profession, String id);



}
