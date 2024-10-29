package com.gai_app.gai_owners.mapper;

import com.gai_app.gai_owners.entity.Gender;
import com.gai_app.gai_owners.entity.Owner;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class OwnerRowMapper implements RowMapper<Owner> {

    @Override
    public Owner mapRow(ResultSet rs, int rowNum) throws SQLException {
        Owner owner = new Owner();
        owner.setId(rs.getLong("id"));
        owner.setName(rs.getString("name"));
        owner.setDob(rs.getDate("dob").toLocalDate());

        String genderString = rs.getString("gender");
        owner.setGender(Gender.valueOf(genderString));
        return owner;
    }
}
