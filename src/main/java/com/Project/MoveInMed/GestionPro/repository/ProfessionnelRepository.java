package com.Project.MoveInMed.GestionPro.repository;

import com.Project.MoveInMed.GestionPro.entity.Professionnel;


import java.util.List;

public interface ProfessionnelRepository  {
    Professionnel Create(Professionnel professionnel);
    Professionnel getByID(String number);
    List<Professionnel> list();
    Professionnel Update(Professionnel professionnel, String number);

}
