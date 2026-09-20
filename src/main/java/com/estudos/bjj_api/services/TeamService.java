package com.estudos.bjj_api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.estudos.bjj_api.entities.Team;
import com.estudos.bjj_api.repositories.TeamRepository;

@Service

public class TeamService{
    private final TeamRepository teamRepository;
    public TeamService(TeamRepository teamRepository){
        this.teamRepository = teamRepository;
    }
    public List<Team> getAllTeams(){
        return teamRepository.findAll();
    }
    public Team insertTeam (Team team){
        return  teamRepository.save(team);
    }
    public Optional <Team> readById(Long id){
        return teamRepository.findById(id);
    }
    public void delTeam(Long id){
        teamRepository.deleteById(id);
    }
    public Team updateTeam(Long id, Team team){
        team.setId(id);
        return teamRepository.save(team);
    }
}
