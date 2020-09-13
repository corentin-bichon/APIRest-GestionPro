package com.Project.MoveInMed.GestionPro.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Entity class for defined professionnel's informations who we need, mapped with the database table TBProfessionnel
 */
@Entity
public class Professionnel {

    public Professionnel(Long id, String name,String firstName, String email, String numTel, String adresse, String profession) {
        this.id = id;
        this.firstName = firstName;
        this.Name = name;
        this.email = email;
        this.numTel = numTel;
        this.adresse = adresse;
        this.profession = profession;
    }

    public Professionnel() {
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String Name;
    private String email ;
    private String numTel ;
    private String adresse ;
    private String profession ;

    //private String[] ListProfession = { "Médécin" , "Généraliste" , "Chirurgien" , "Infermier" , "Kinésithérapeute" , "Assistante sociale", "Psychologue"};


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getName() {
        return Name;
    }

    public void setName(String lastName) {
        this.Name = lastName;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

}

