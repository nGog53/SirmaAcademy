package com.example.restdbapp.repositories;

import com.example.restdbapp.models.Show;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ShowRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public ShowRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void saveShowToDatabase(Show show) {
        String sql = "INSERT INTO shows (title, year, episodes) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, show.getTitle(), show.getYear(), show.getEpisodes());
    }

    public void deleteShowFromDatabase(String id) {
        String sql = "DELETE FROM shows WHERE id=?";
        jdbcTemplate.update(sql, Integer.parseInt(id));
    }
}
