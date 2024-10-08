package com.football.api.service;

import com.football.api.model.Team;
import com.football.api.model.dto.TeamDto;
import com.football.api.repository.csv.TeamCsvReader;
import com.football.api.repository.jpa.TeamJpaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TeamService {
    public static final String TEAMS_FILE = "src/main/resources/csv/teams.csv";

    @Autowired
    TeamJpaRepository teamJpaRepository;

    @Autowired
    TeamCsvReader teamCsvReader;

    public void listToDatabase() throws IOException {
        try {
            ArrayList<Team> teams = teamCsvReader.csvToList(TEAMS_FILE);
            teamJpaRepository.saveAll(teams);
        } catch (IOException e) {
            throw new IOException("Invalid data and/or file path");
        }
    }

    public List<Team> getAllTeams() {
       return teamJpaRepository.findAll();
    }

    public TeamDto getTeam(Long id) {
         Team team = teamJpaRepository.findById(id).orElseThrow(EntityNotFoundException::new);
         return new TeamDto(team);
    }

    public Team addTeam(Team team) {
        return teamJpaRepository.save(team);
    }

    public Team updateTeam(Long id, Team updateTeam) {
        Team team = teamJpaRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        team.setName(updateTeam.getName());
        team.setManager(updateTeam.getManager());
        team.setTournamentGroup(updateTeam.getTournamentGroup());
        return teamJpaRepository.save(team);
    }

    public void deleteTeam(Long id) {
        if (teamJpaRepository.existsById(id)) {
            teamJpaRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException();
        }
    }
}
