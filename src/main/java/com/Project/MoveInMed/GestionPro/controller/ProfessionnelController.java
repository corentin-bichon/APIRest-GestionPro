package com.Project.MoveInMed.GestionPro.controller;

import com.Project.MoveInMed.GestionPro.entity.Professionnel;
import com.Project.MoveInMed.GestionPro.repository.ProfessionnelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProfessionnelController {
	@Autowired
	ProfessionnelRepository repository;

	@GetMapping("/professionnels")
	public List<Professionnel> list(){
		List<Professionnel> professionnels =new ArrayList<>();
		repository.list().forEach(professionnels::add);
		return professionnels;
	}

	@PostMapping("/professionnels")
	public Professionnel AddProfessionnel(@RequestBody Professionnel professionnel){
		repository.Create(professionnel);

		return professionnel;
	}

	@GetMapping("/professionnels/{id}")
	public Professionnel ReadProById(@PathVariable String id){
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

	@DeleteMapping("/professionnels/{id}")
	public void DeleteById(@PathVariable String id){
		List<Professionnel> professionnels =new ArrayList<>();
		repository.Delete(id);
	}


	@PutMapping("/professionnels/{id}")
	public Professionnel Update(@PathVariable String id, @RequestBody Professionnel professionnel){
		return repository.Update(professionnel, id);
	}


	//TODO Enum (pour amélioration)
	@GetMapping("/professionnels/sort/{choix}")
	public List<Professionnel> SortBy(@PathVariable String choix){
		switch(choix) {
			case "name" : choix="PRO_NAME";
			break;
			case "firstname" : choix="PRO_FIRSTNAME" ;
			break;
			case "profession" : choix="PRO_JOB" ;
			break;

			default: choix="PRO_ID" ;
		}

		List<Professionnel> professionnels =new ArrayList<>();
		repository.Sort(choix).forEach(professionnels::add);
		return professionnels;
	}
}
