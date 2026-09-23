package com.estudos.bjj_api.services;

import org.springframework.stereotype.Service;

import com.estudos.bjj_api.entities.Athlete;
import com.estudos.bjj_api.repositories.AthleteRepository;

import java.util.List;
import java.util.Optional;

@Service 
public class  AthleteService {
    private final AthleteRepository athleteRepository;
    public AthleteService (AthleteRepository athleteRepository){
        this.athleteRepository= athleteRepository;
    }
    public List<Athlete>getAllAthletes(){
        return athleteRepository.findAll();
    }
    public Athlete insertAthlete(Athlete athlete){
        return athleteRepository.save(athlete);
    }
    public Optional <Athlete> readById(Long id){
        return athleteRepository.findById(id);
    }
    public void delAthlete(Long id){
        athleteRepository.deleteById(id);
    }
    public Athlete updateAthlete(Long id, Athlete athlete){
        athlete.setId(id);
        return athleteRepository.save(athlete);
    }

}
