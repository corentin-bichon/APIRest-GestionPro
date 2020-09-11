package com.Project.MoveInMed.GestionPro.repository.database;

import com.Project.MoveInMed.GestionPro.entity.Professionnel;
import com.Project.MoveInMed.GestionPro.repository.ProfessionnelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.swing.*;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class ProfessionnelDatabase implements ProfessionnelRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Professionnel> list() {

        return jdbcTemplate.query("SELECT * FROM PRO" ,
                (rs,rowNum) ->new Professionnel(rs.getLong("PRO_ID"),
                        rs.getString("PRO_NAME"), rs.getString("PRO_FIRSTNAME"), rs.getString("PRO_EMAIL"),rs.getString("PRO_PHONE"), rs.getString("PRO_ADRESSE"), rs.getString("PRO_JOB")  ));
    }


    @Override
    public Professionnel getByID(String number) {

        return jdbcTemplate.queryForObject("SELECT * FROM PRO WHERE PRO_ID=? " ,
                new Object[]{number},
                (rs,rowNum) -> new Professionnel(rs.getLong("PRO_ID"),
                   rs.getString("PRO_NAME"), rs.getString("PRO_FIRSTNAME"), rs.getString("PRO_EMAIL"),rs.getString("PRO_PHONE"), rs.getString("PRO_ADRESSE"), rs.getString("PRO_JOB")  ));
    }

    @Override
    public List<Professionnel> getByName(String nom) {
        return jdbcTemplate.query("SELECT * FROM PRO WHERE PRO_NAME=? " ,
                new Object[]{nom},
                (rs,rowNum) -> new Professionnel(rs.getLong("PRO_ID"),
                        rs.getString("PRO_NAME"), rs.getString("PRO_FIRSTNAME"), rs.getString("PRO_EMAIL"),rs.getString("PRO_PHONE"), rs.getString("PRO_ADRESSE"), rs.getString("PRO_JOB")  ));
    }

    @Override
    public List<Professionnel> getByJob(String profession) {
        return jdbcTemplate.query("SELECT * FROM PRO WHERE PRO_JOB=? " ,
                new Object[]{profession},
                (rs,rowNum) -> new Professionnel(rs.getLong("PRO_ID"),
                        rs.getString("PRO_NAME"), rs.getString("PRO_FIRSTNAME"), rs.getString("PRO_EMAIL"),rs.getString("PRO_PHONE"), rs.getString("PRO_ADRESSE"), rs.getString("PRO_JOB")  ));

    }


    @Override
    public Professionnel Create(Professionnel professionnel) {

        KeyHolder kh=new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {

                    PreparedStatement ps = connection.prepareStatement("INSERT INTO PRO(PRO_NAME, PRO_FIRSTNAME, PRO_EMAIL , PRO_PHONE , PRO_JOB , PRO_ADRESSE ) VALUES (?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1, professionnel.getName());
                    ps.setString(2, professionnel.getFirstName());
                    ps.setString(3, professionnel.getEmail());
                    ps.setString(4, professionnel.getNumTel());
                    ps.setString(5, professionnel.getProfession());
                    ps.setString(6, professionnel.getAdresse());
                    return ps;
                } , kh);

        professionnel.setId(kh.getKey().longValue());

        return professionnel;
    }


    @Override
    public Professionnel Update(Professionnel professionnel, String number) {

        jdbcTemplate.update(connection -> {

            PreparedStatement ps = connection.prepareStatement("UPDATE PRO SET PRO_NAME=? , PRO_FIRSTNAME=?, PRO_PHONE=?, PRO_EMAIL=?, PRO_JOB=?, PRO_ADRESSE=? WHERE PRO_ID=?");
            ps.setString(1, professionnel.getName());
            ps.setString(2, professionnel.getFirstName());
            ps.setString(3, professionnel.getNumTel());
            ps.setString(4, professionnel.getEmail());
            ps.setString(5, professionnel.getProfession());
            ps.setString(6, professionnel.getAdresse());
            ps.setString(7,number);
            return ps;
        } );

        return professionnel;
    }


    @Override
    public void Delete(String id) {

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM PRO WHERE PRO_ID=?");
            ps.setString(1,id);
            return ps;
        } );
    }


    @Override
    public List<Professionnel> Sort(String name) {

        return jdbcTemplate.query("SELECT * FROM PRO ORDER BY " + name,
                (rs, rowNum) -> new Professionnel(rs.getLong("PRO_ID"),
                        rs.getString("PRO_NAME"), rs.getString("PRO_FIRSTNAME"), rs.getString("PRO_EMAIL"), rs.getString("PRO_PHONE"), rs.getString("PRO_ADRESSE"), rs.getString("PRO_JOB")));
    }
}
