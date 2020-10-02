package com.Project.MoveInMed.GestionPro.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ProPat {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long rNum;
    private Long pro_id;
    private Long pat_id ;
    private String sickness ;

    public ProPat(Long rNum, Long pro_id, Long pat_id, String sickness) {
        this.rNum = rNum;
        this.pro_id = pro_id;
        this.pat_id = pat_id;
        this.sickness = sickness;
    }

    public ProPat() {
    }

    public Long getrNum() {
        return rNum;
    }

    public void setrNum(Long rNum) {
        this.rNum = rNum;
    }

    public Long getPro_id() {
        return pro_id;
    }

    public void setPro_id(Long pro_id) {
        this.pro_id = pro_id;
    }

    public Long getPat_id() {
        return pat_id;
    }

    public void setPat_id(Long pat_id) {
        this.pat_id = pat_id;
    }

    public String getSickness() {
        return sickness;
    }

    public void setSickness(String sickness) {
        this.sickness = sickness;
    }
}


