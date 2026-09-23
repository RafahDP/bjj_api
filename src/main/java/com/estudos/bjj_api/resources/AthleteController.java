package com.estudos.bjj_api.resources;

import java.util.Optional;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.estudos.bjj_api.services.AthleteService;
import com.estudos.bjj_api.entities.Athlete;
@RestController 
@RequestMapping(value = "/athletes")
public class AthleteController {
    private final AthleteService athleteService;
     public AthleteController(AthleteService athleteService){
        this.athleteService = athleteService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<Athlete> readById(@PathVariable Long id){
        Optional<Athlete> athlete = athleteService.readById(id);
        if(athlete.isPresent()){
            return ResponseEntity.ok(athlete.get());
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping
    public List<Athlete> athleteList(){
        return athleteService.getAllAthletes();
    }
     @PostMapping
    public ResponseEntity<Athlete> insertAthlete(@RequestBody Athlete athlete){
       Athlete savadAthlete = athleteService.insertAthlete(athlete);
       return ResponseEntity.status(HttpStatus.CREATED).body(savadAthlete);
    }
     @PutMapping("/{id}")
    public ResponseEntity<Athlete> updateAthlete(@PathVariable  Long id, @RequestBody Athlete athlete){
        Athlete updateAthlete = athleteService.updateAthlete(id,athlete);
        return ResponseEntity.ok(updateAthlete);
    }
     @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAthlete(@PathVariable Long id){
        Optional<Athlete> athlete = athleteService.readById(id);
        if( athlete.isPresent()){
            athleteService.delAthlete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();    
    }
    
}
