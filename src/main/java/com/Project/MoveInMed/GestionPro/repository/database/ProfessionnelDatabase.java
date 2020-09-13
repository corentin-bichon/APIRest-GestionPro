package com.Project.MoveInMed.GestionPro.repository.database;

import com.Project.MoveInMed.GestionPro.entity.Professionnel;
import com.Project.MoveInMed.GestionPro.repository.ProfessionnelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

/**
 * Class that updates the database's informations according to a paramater passed in the controller 
 */
@Repository
public class ProfessionnelDatabase implements ProfessionnelRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Function used by AddProfessionnel for create professionnal's account
     * @param professionnel professionnal's informations
     * @return the informations of the new professional's account
     */
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

    /**
     * Function used by DeleteById for Delete professional's informations using his ID
     * @param id Unique number of a professional
     */
    @Override
    public void Delete(String id) {

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM PRO WHERE PRO_ID=?");
            ps.setString(1,id);
            return ps;
        } );
    }


    /**
     * Function used by UpdateById for Update professional's informations using his ID
     * @param number Unique number of a professional
     * @param professionnel informations to change
     * @return the new informations of the professional
     */
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

    /**
     * Function used by SortBy for Display professionnal's informations, they can be sorted by :
     * @param choice groups professionnals by name, firstname or profession
     * @param name select all the professionnals whose name is passed in parameter
     * @param profession name select all the professionnals whose profession is passed in parameter
     * @param id select the professionnal whose id is passed in parameter
     * @return the list of the professionnals found
     */
    @Override
    public List<Professionnel> Sort(String choice,String  name ,String profession, String id) {

        return jdbcTemplate.query("SELECT * FROM PRO WHERE PRO_NAME LIKE '"+ name +"' AND PRO_ID LIKE '"+ id + "' AND PRO_JOB LIKE '" + profession + "' ORDER BY " + choice ,
                (rs, rowNum) -> new Professionnel(rs.getLong("PRO_ID"),
                        rs.getString("PRO_NAME"), rs.getString("PRO_FIRSTNAME"), rs.getString("PRO_EMAIL"), rs.getString("PRO_PHONE"), rs.getString("PRO_ADRESSE"), rs.getString("PRO_JOB")));
    }

    /*
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

     */
}
