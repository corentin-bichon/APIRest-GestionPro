package com.Project.MoveInMed.GestionPro.controller;


import com.Project.MoveInMed.GestionPro.entity.Professionnel;
import com.Project.MoveInMed.GestionPro.repository.ProfessionnelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Class responsible to Manage the flow of the Application
 */
@RestController
@RequestMapping("/api")
public class ProfessionnelController {

	@Autowired
	ProfessionnelRepository repository;

	/**
	 * Enum list used to define the parameters that can be used for grouping professionals
	 */
	enum SortChoice {
		id,
		name,
		firstname,
		profession
	}

	/**
	 * Create an account for the professionnal
	 * @param professionnel professionnal's informations
	 * @return the informations of the new professional's account
	 */
	@PostMapping("/professionnels")
	public Professionnel AddProfessionnel(@RequestBody Professionnel professionnel){
		repository.Create(professionnel);

		return professionnel;
	}


	/**
	 * Delete professional's informations using his ID
	 * @param id Unique number of a professional
	 */
	@DeleteMapping("/professionnels/{id}")
	public void DeleteById(@PathVariable String id){
		List<Professionnel> professionnels =new ArrayList<>();
		repository.Delete(id);
	}


	/**
	 * Update professional's informations using his ID
	 * @param id id Unique number of a professional
	 * @param professionnel informations to change
	 * @return the new informations of the professional
	 */
	@PutMapping("/professionnels/{id}")
	public Professionnel Update(@PathVariable String id, @RequestBody Professionnel professionnel){
		return repository.Update(professionnel, id);
	}


	/**
	 * Display professionnal's informations, they can be sorted by :
	 * @param choice groups professionnals by name, firstname or profession
	 * @param name select all the professionnals whose name is passed in parameter
	 * @param profession name select all the professionnals whose profession is passed in parameter
	 * @param id select the professionnal whose id is passed in parameter
	 * @return the list of the professionnals found
	 */
	@GetMapping("/professionnels")
	public List<Professionnel> SortBy(@RequestParam(defaultValue = "id") SortChoice choice, @RequestParam(defaultValue = "%") String name,
									  @RequestParam(defaultValue = "%")  String profession, @RequestParam(defaultValue = "%")  String id  ){
		String sqlChoice;

		switch(choice) {
			case name : sqlChoice="PRO_NAME";
			break;
			case firstname :sqlChoice="PRO_FIRSTNAME" ;
			break;
			case profession : sqlChoice="PRO_JOB" ;
			break;

			default: sqlChoice="PRO_ID" ;
		}

		List<Professionnel> professionnels =new ArrayList<>();
		repository.Sort(sqlChoice,name,profession,id).forEach(professionnels::add);
		return professionnels;
	}

	//Old Method, now grouped in SortBy
	/*
	@GetMapping("/professionnels")
	public List<Professionnel> list(){
		List<Professionnel> professionnels =new ArrayList<>();
		repository.list().forEach(professionnels::add);
		return professionnels;
	}

	@GetMapping("/professionnels/{id}")
	public Professionnel ReadProById(@PathVariable String id ){
		return repository.getByID(id);

	}

	@GetMapping("/professionnels/name/{nom}")
	public List<Professionnel> ReadProByName(@PathVariable String nom){
		List<Professionnel> professionnels =new ArrayList<>();
		repository.getByName(nom).forEach(professionnels::add);
		return professionnels;
	}

	@GetMapping("/professionnels/job/{profession}")
	public List<Professionnel> ReadProByJob(@PathVariable String profession){
		List<Professionnel> professionnels =new ArrayList<>();
		repository.getByJob(profession).forEach(professionnels::add);
		return professionnels;
	}
	*/

}
