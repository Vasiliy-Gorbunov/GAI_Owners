package com.gai_app.gai_owners.repository;

import com.gai_app.gai_owners.entity.Owner;
import com.gai_app.gai_owners.mapper.OwnerRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class OwnerJdbcRepository implements OwnerRepository{

    private final JdbcTemplate jdbcTemplate;

    public OwnerJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Owner> findAll() {
        String sql = "SELECT * FROM owner";
        return jdbcTemplate.query(sql, new OwnerRowMapper());
    }

    @Override
    public Optional<Owner> findById(Long id) {
        String sql = "SELECT * FROM owner WHERE id = ?";
        List<Owner> owners = jdbcTemplate.query(sql, new Object[]{id}, new OwnerRowMapper());
        return owners.stream().findFirst();
    }

    @Override
    public Owner save(Owner owner) {
        if (owner.getId() == null || findById(owner.getId()).isEmpty()) {
            String sql = "INSERT INTO owner (name, dob, gender) VALUES (?, ?, ?)";
            jdbcTemplate.update(sql, owner.getName(), owner.getDob(), owner.getGender().name());
            String sqlSelect = "SELECT * FROM Owner ORDER BY id DESC LIMIT 1";
            List<Owner> owners = jdbcTemplate.query(sqlSelect, new OwnerRowMapper());
            return owners.stream().findFirst().get();
        } else {
            String sql = "UPDATE owner SET name = ?, dob = ?, gender = ?, WHERE id = ?";
            jdbcTemplate.update(sql,
                    owner.getName(),
                    owner.getDob(),
                    owner.getGender().name(),
                    owner.getId());
        }
        return findById(owner.getId()).get();
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM owner WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
