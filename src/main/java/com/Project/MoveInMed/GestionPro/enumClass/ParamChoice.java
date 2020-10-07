package com.Project.MoveInMed.GestionPro.enumClass;

public class ParamChoice {

    /**
     * Enum list used to define the parameters that can be used for grouping professionals
     */
     public enum professionalSortChoice {
        id,
        name,
        firstname,
        profession
    }


    /**
    * Enum list used to define the parameters that can be used for profession
    */
    public enum professionalJobs {
    Médecin_généraliste("Médecin_généraliste"),
    Chirurgien("Chirurgien"),
    Infirmier("Infirmier"),
    kinésithérapeute("kinésithérapeute"),
    Assistante_Sociale("Assistante_sociale"),
    Psychologue("Psychologue"),
    Percent("%");
    private String value;
    private professionalJobs(String value)
    {
        this.value = value;
    }

    public String toString()
    {
        return this.value;
    }
}


    public enum patientSortChoice {
        id,
        name,
        firstname,
    }

}
