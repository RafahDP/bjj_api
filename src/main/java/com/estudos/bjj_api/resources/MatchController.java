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

import com.estudos.bjj_api.entities.Match;
import com.estudos.bjj_api.services.MatchService;

@CrossOrigin(origins = "*")
@RestController 
@RequestMapping(value = "/matches")
public class MatchController {
    private final MatchService matchService;
    public MatchController(MatchService matchService){
        this.matchService = matchService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<Match> readById(@PathVariable Long id){
        Optional<Match> match = matchService.readById(id);
    if(match.isPresent()){
            return ResponseEntity.ok(match.get());
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping
    public List<Match> matchList(){
        return matchService.getAllMatch();
    }
    @PostMapping
    public ResponseEntity<Match> insertMatch(@RequestBody Match match){
       Match savedMatch = matchService.insertMatch(match);
       return ResponseEntity.status(HttpStatus.CREATED).body(savedMatch);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Match> updateMatch(@PathVariable  Long id, @RequestBody Match match){
        Match updateMatch = matchService.updateMatch(id,match);
        return ResponseEntity.ok(updateMatch);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMatch(@PathVariable Long id){
        Optional<Match> match = matchService.readById(id);
        if( match.isPresent()){
            matchService.delMatch(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();    
    }
    
}
