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

@Repository
public class ProfessionnelDatabase implements ProfessionnelRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Professionnel> list() {
        return jdbcTemplate.query("SELECT PRO_NAME, PRO_FIRSTNAME FROM PRO" ,
                (rs,rowNum) -> new Professionnel(rs.getString("PRO_NAME"), rs.getString("PRO_FIRSTNAME")));
    }


    @Override
    public Professionnel getByID(String number) {
        return jdbcTemplate.queryForObject("SELECT PRO_NAME, PRO_FIRSTNAME, PRO_ID FROM PRO WHERE PRO_ID=? " ,
                new Object[]{number},
                (rs,rowNum) -> new Professionnel(rs.getLong("PRO_ID"),
                   rs.getString("PRO_NAME"), rs.getString("PRO_FIRSTNAME")));
    }


    @Override
    public Professionnel Create(Professionnel professionnel) {

        KeyHolder kh=new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
                    PreparedStatement ps = connection.prepareStatement("INSERT INTO PRO(PRO_NAME, PRO_FIRSTNAME) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1, professionnel.getName());
                    ps.setString(2, professionnel.getFirstName());
                    return ps;
                } , kh);

        professionnel.setId(kh.getKey().longValue());

        return professionnel;
    }


    @Override
    public Professionnel Update(Professionnel professionnel, String number) {

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement("UPDATE INTO PRO SET PRO_NAME=? PRO_FIRSTNAME=? WHERE PRO_ID=?");
            ps.setString(1, professionnel.getName());
            ps.setString(2, professionnel.getFirstName());
            ps.setString(3,number);
            return ps;
        } );

        return professionnel;
    }

}
