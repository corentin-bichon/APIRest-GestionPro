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
	public Professionnel ReadPro(@PathVariable String id){
		return repository.getByID(id);

	}


	@PutMapping("/professionnels/{id}")
	public Professionnel Update(@PathVariable String id, @RequestBody Professionnel professionnel){
		return repository.Update(professionnel, id);
	}

}
