package com.Project.MoveInMed.GestionPro.controller;


import com.Project.MoveInMed.GestionPro.enumClass.ParamChoice;
import com.Project.MoveInMed.GestionPro.entity.Professional;
import com.Project.MoveInMed.GestionPro.repository.database.ProfessionalDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Class responsible to Manage the flow of the Application
 */
@RestController
@RequestMapping("/api")
public class ProfessionalController {

	@Autowired
	private ProfessionalDatabase repository;

	/**
	 * Create an account for the professional
	 * @param professional professional's information
	 * @return the information of the new professional's account
	 */
	@PostMapping("/professionals")
	public Professional addProfessional(@RequestBody Professional professional){
		return repository.create(professional);
	}


	/**
	 * Delete professional's information using his ID
	 * @param id Unique number of a professional
	 */
	@DeleteMapping("/professionals/{id}")
	public void deleteById(@PathVariable Long id){
		repository.delete(id);
	}


	/**
	 * Update professional's information using his ID
	 * @param id id Unique number of a professional
	 * @param professional information to change
	 * @return the new information of the professional
	 */
	@PutMapping("/professionals/{id}")
	public Professional update(@PathVariable Long id, @RequestBody Professional professional){
		return repository.update(professional, id);
	}


	/**
	 * Display professional's information, they can be sorted by :
	 * @param choice groups professionals by name, firstname or profession
	 * @param name select all the professionals whose name is passed in parameter
	 * @param profession name select all the professionals whose profession is passed in parameter
	 * @param id select the professional whose id is passed in parameter
	 * @return the list of the professionals found
	 */
	@GetMapping("/professionals")
	public List<Professional> sortBy(@RequestParam(defaultValue = "id") ParamChoice.professionalSortChoice choice,
									 @RequestParam(defaultValue = "%") String name,
									 @RequestParam(defaultValue = "Percent" ) ParamChoice.professionalJobs profession,
									 @RequestParam(defaultValue = "%")  String id  ){


		return repository.sort(choice,name,profession,id);
	}
}
