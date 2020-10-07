package com.Project.MoveInMed.GestionPro.repository.database;

import com.Project.MoveInMed.GestionPro.entity.ProPat;
import com.Project.MoveInMed.GestionPro.enumClass.ParamChoice;
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
public class ProPatDatabase {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public ProPat createRelation(ProPat relation) {

        KeyHolder kh=new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {

            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO propat(pat_id, pro_id, sickness, regular_doctor ) " +
                            "VALUES (?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, relation.getPat_id());
            ps.setLong(2, relation.getPro_id());
            ps.setString(3, relation.getSickness());
            ps.setBoolean(4, relation.isRegular_doctor());
            return ps;
        } , kh);

        relation.setrNum(kh.getKey().longValue());

        return relation;
    }

    public void deleteRelation(String rNum, String pat_id, String pro_id) {

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM propat WHERE rNum LIKE ? AND pat_id LIKE ? AND pro_id LIKE ?");
            ps.setString(1,rNum);
            ps.setString(2,pat_id);
            ps.setString(3,pro_id);
            return ps;
        } );
    }

    public ProPat updateRelation(ProPat relation, Long rNum) {

        jdbcTemplate.update(connection -> {

            PreparedStatement ps = connection.prepareStatement(
                    "UPDATE propat SET pat_id=? , pro_id=? , sickness=? , regular_doctor=? " +
                            "WHERE rNum=?");
            ps.setLong(1, relation.getPat_id());
            ps.setLong(2, relation.getPro_id());
            ps.setString(3, relation.getSickness());
            ps.setBoolean(4, relation.isRegular_doctor());
            ps.setLong(5,rNum);
            return ps;
        } );

        return relation;
    }

    public List<ProPat> sortRelation( String rNum, String pat_id, String pro_id, String sickness) {

        return jdbcTemplate.query("SELECT * FROM propat " +
                        "WHERE rNum LIKE '"+rNum +"' AND pat_id LIKE '"+ pat_id + "'AND pro_id LIKE '"+ pro_id + "' AND sickness LIKE '%" + sickness +"%'",
                (rs, rowNum) -> new ProPat(rs.getLong("rNum"),
                        rs.getLong("pat_id"), rs.getLong("pro_id"), rs.getString("sickness"), rs.getBoolean("regular_doctor")));
    }
}

