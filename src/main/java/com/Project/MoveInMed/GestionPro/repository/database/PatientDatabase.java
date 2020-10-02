package com.Project.MoveInMed.GestionPro.repository.database;

import com.Project.MoveInMed.GestionPro.entity.Patient;
import com.Project.MoveInMed.GestionPro.enumClass.ParamChoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class PatientDatabase {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Patient create(Patient patient) {

        KeyHolder kh=new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {

            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO Patient(name, firstname, email , phone , address ) " +
                            "VALUES (?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, patient.getName());
            ps.setString(2, patient.getFirstname());
            ps.setString(3, patient.getEmail());
            ps.setString(4, patient.getPhone());
            ps.setString(5, patient.getAddress());
            return ps;
        } , kh);

        patient.setId(kh.getKey().longValue());

        return patient;
    }


    public void delete(Long id) {

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM Patient WHERE id=?");
            ps.setLong(1,id);
            return ps;
        } );
    }


    public Patient update(Patient patient, Long id) {

        jdbcTemplate.update(connection -> {

            PreparedStatement ps = connection.prepareStatement(
                    "UPDATE Patient SET name=? , firstname=?, phone=?, email=?, address=? " +
                            "WHERE id=?");
            ps.setString(1, patient.getName());
            ps.setString(2, patient.getFirstname());
            ps.setString(3, patient.getPhone());
            ps.setString(4, patient.getEmail());
            ps.setString(5, patient.getAddress());
            ps.setLong(6,id);
            return ps;
        } );

        return patient;
    }


    public List<Patient> sort(ParamChoice.patientSortChoice choice, String  name , String id) {

        return jdbcTemplate.query("SELECT * FROM Patient " +
                        "WHERE name LIKE '%"+ name +"%' AND id LIKE '"+ id +
                        "' ORDER BY " + choice ,
                (rs, rowNum) -> new Patient(rs.getLong("id"),
                        rs.getString("name"), rs.getString("firstname"), rs.getString("email"),
                        rs.getString("phone"), rs.getString("address")));
    }




}
