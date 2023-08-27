package com.test.dao;

import com.test.model.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TestDAO {

    JdbcTemplate jdbcTemplate;

    @Autowired
    public TestDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Test> index() {
        return jdbcTemplate.query("SELECT * FROM test",
                new BeanPropertyRowMapper<>(Test.class));
    }


    public Test show(int id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM test WHERE id=?",
                    new BeanPropertyRowMapper<>(Test.class), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }


    public void release(int id){
        jdbcTemplate.update("UPDATE Test SET isadult=? WHERE id=?",null,id);
    }

    public void isAdult(int id){
        jdbcTemplate.update("UPDATE Test SET isadult=? WHERE id=?","yes",id);
    }


}
