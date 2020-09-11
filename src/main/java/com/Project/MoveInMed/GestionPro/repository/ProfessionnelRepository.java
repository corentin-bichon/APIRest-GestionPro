package com.Project.MoveInMed.GestionPro.repository;

import com.Project.MoveInMed.GestionPro.entity.Professionnel;


import java.util.List;

public interface ProfessionnelRepository  {
    Professionnel Create(Professionnel professionnel);
    Professionnel getByID(String number);
    List<Professionnel> getByName(String nom) ;
    List<Professionnel> getByJob(String profession);
    List<Professionnel> list();
    Professionnel Update(Professionnel professionnel, String number);
    void Delete(String name);

}
