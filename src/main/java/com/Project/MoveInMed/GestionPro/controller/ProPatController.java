package com.Project.MoveInMed.GestionPro.controller;

import com.Project.MoveInMed.GestionPro.entity.ProPat;
import com.Project.MoveInMed.GestionPro.enumClass.ParamChoice;
import com.Project.MoveInMed.GestionPro.repository.database.ProPatDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Class responsible to Manage the flow of the Application
 */
@RestController
@RequestMapping("/api3")
public class ProPatController {

    @Autowired
    private ProPatDatabase repository;


    @PostMapping("/relation")
    public ProPat addRelation(@RequestBody ProPat relation){
        return repository.createRelation(relation);
    }


    @DeleteMapping("/relation/{rNum}")
    public void deleteByrNum(@PathVariable Long rNum){
        repository.deleteRelation(rNum);
    }



    @PutMapping("/relation/{id}")
    public ProPat updatePro(@PathVariable Long rNum, @RequestBody ProPat relation){
        return repository.updateRelation(relation, rNum);
    }


    @GetMapping("/relation")
    public List<ProPat> sortProBy(@RequestParam(defaultValue = "%") String rNum,
                                        @RequestParam(defaultValue = "%") String pat_id,
                                        @RequestParam(defaultValue = "%" ) String pro_id,
                                        @RequestParam(defaultValue = "%")  String sickness  ){


        return repository.sortRelation(rNum, pat_id, pro_id, sickness);
    }

}
