package com.Project.MoveInMed.GestionPro.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Entity class for defined professional's information who we need, mapped with the database table TBProfessionnel
 */
@Entity
public class Professional {

    public Professional(Long id, String name, String firstname, String email, String phone, String address, String profession) {
        this.id = id;
        this.firstname = firstname;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.profession = profession;
    }

    public Professional() {
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String name;
    private String email ;
    private String phone;
    private String address;
    private String profession ;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getName() {
        return name;
    }

    public void setName(String lastName) {
        this.name = lastName;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}

