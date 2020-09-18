package com.Project.MoveInMed.GestionPro.repository.database;

import com.Project.MoveInMed.GestionPro.enumClass.ParamChoice;
import com.Project.MoveInMed.GestionPro.entity.Professional;
import com.Project.MoveInMed.GestionPro.repository.ProfessionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

/**
 * Class that updates the database's information according to a parameter passed in the controller
 */
@Repository
public class ProfessionalDatabase implements ProfessionalRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Function used by AddProfessional for create professional's account
     * @param professional professional's information
     * @return the information of the new professional's account
     */
    @Override
    public Professional create(Professional professional) {

        KeyHolder kh=new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {

                    PreparedStatement ps = connection.prepareStatement(
                            "INSERT INTO Professional(name, firstname, email , phone , profession , address ) " +
                                    "VALUES (?,?,?,?,?,?)",
                            Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1, professional.getName());
                    ps.setString(2, professional.getFirstname());
                    ps.setString(3, professional.getEmail());
                    ps.setString(4, professional.getPhone());
                    ps.setString(5, professional.getProfession());
                    ps.setString(6, professional.getAddress());
                    return ps;
                } , kh);

        professional.setId(kh.getKey().longValue());

        return professional;
    }

    /**
     * Function used by DeleteById for Delete professional's information using his ID
     * @param id Unique number of a professional
     */
    @Override
    public void delete(Long id) {

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM Professional WHERE id=?");
            ps.setLong(1,id);
            return ps;
        } );
    }


    /**
     * Function used by UpdateById for Update professional's information using his ID
     * @param id Unique number of a professional
     * @param professional information to change
     * @return the new information of the professional
     */
    @Override
    public Professional update(Professional professional, Long id) {

        jdbcTemplate.update(connection -> {

            PreparedStatement ps = connection.prepareStatement(
                    "UPDATE Professional SET name=? , firstname=?, phone=?, email=?, profession=?, address=? " +
                            "WHERE id=?");
            ps.setString(1, professional.getName());
            ps.setString(2, professional.getFirstname());
            ps.setString(3, professional.getPhone());
            ps.setString(4, professional.getEmail());
            ps.setString(5, professional.getProfession());
            ps.setString(6, professional.getAddress());
            ps.setLong(7,id);
            return ps;
        } );

        return professional;
    }

    /**
     * Function used by SortBy for Display professional's information, they can be sorted by :
     * @param choice groups professionals by name, firstname or profession
     * @param name select all the professionals whose name is passed in parameter
     * @param profession name select all the professionals whose profession is passed in parameter
     * @param id select the professional whose id is passed in parameter
     * @return the list of the professionals found
     */
    @Override
    public List<Professional> sort(ParamChoice.professionalSortChoice choice, String  name , ParamChoice.professionalJobs profession, String id) {

        return jdbcTemplate.query("SELECT * FROM professional " +
                        "WHERE name LIKE '%"+ name +"%' AND id LIKE '"+ id + "' AND profession LIKE '" + profession.toString() +
                        "' ORDER BY " + choice ,
                (rs, rowNum) -> new Professional(rs.getLong("id"),
                        rs.getString("name"), rs.getString("firstname"), rs.getString("email"),
                        rs.getString("phone"), rs.getString("address"), rs.getString("profession")));
    }
}
