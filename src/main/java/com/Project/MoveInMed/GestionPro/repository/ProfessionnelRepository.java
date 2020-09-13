package com.Project.MoveInMed.GestionPro.repository;

import com.Project.MoveInMed.GestionPro.entity.Professionnel;


import java.util.List;

/**
 * Class parent of all repository's class
 */
public interface ProfessionnelRepository  {
    /*
    Professionnel getByID(String number);
    List<Professionnel> getByName(String nom) ;
    List<Professionnel> getByJob(String profession);
    List<Professionnel> list();
    */
    Professionnel Create(Professionnel professionnel);
    Professionnel Update(Professionnel professionnel, String number);
    void Delete(String name);
    List<Professionnel> Sort(String choice, String  name ,  String profession, String id);



}
