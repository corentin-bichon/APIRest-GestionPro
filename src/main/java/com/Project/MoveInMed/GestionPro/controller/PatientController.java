package com.Project.MoveInMed.GestionPro.controller;

import com.Project.MoveInMed.GestionPro.entity.Patient;
import com.Project.MoveInMed.GestionPro.enumClass.ParamChoice;
import com.Project.MoveInMed.GestionPro.repository.database.PatientDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Class responsible to Manage the flow of the Application
 */
@RestController
@RequestMapping("/api")
public class PatientController {

    @Autowired
    private PatientDatabase repository;

    @PostMapping("/patients")
    public Patient addPatient(@RequestBody Patient patient){
        return repository.create(patient);
    }


    @DeleteMapping("/patients/{id}")
    public void deletePatientById(@PathVariable Long id){
        repository.delete(id);
    }


    @PutMapping("/patients/{id}")
    public Patient updatePatient(@PathVariable Long id, @RequestBody Patient patient){
        return repository.update(patient, id);
    }


    @GetMapping("/patients")
    public List<Patient> sortPatientBy(@RequestParam(defaultValue = "id") ParamChoice.patientSortChoice choice,
                                       @RequestParam(defaultValue = "%") String name,
                                       @RequestParam(defaultValue = "%")  String id  ){


        return repository.sort(choice,name,id);
    }

}
