package com.estudos.bjj_api.resources;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estudos.bjj_api.entities.Team;
import com.estudos.bjj_api.services.TeamService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/teams")
public class TeamController{
    private final TeamService teamService;
    public TeamController(TeamService teamService){
        this.teamService = teamService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<Team> readById(@PathVariable Long id){
        Optional<Team> team = teamService.readById(id);
        if(team.isPresent()){
            return ResponseEntity.ok(team.get());
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping
    public List<Team> teamList(){
        return teamService.getAllTeams();
    }
    @PostMapping
    public ResponseEntity<Team> insertTeam(@RequestBody Team team){
       Team savedTeam = teamService.insertTeam(team);
       return ResponseEntity.status(HttpStatus.CREATED).body(savedTeam);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Team> updateTeam(@PathVariable  Long id, @RequestBody Team team){
        Team updateTeam = teamService.updateTeam(id,team);
        return ResponseEntity.ok(updateTeam);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeam(@PathVariable Long id){
        Optional<Team> team = teamService.readById(id);
        if( team.isPresent()){
            teamService.delTeam(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();    
    }
    
}