package com.estudos.bjj_api.services;

import java.util.List;
import java.util.Optional;

import com.estudos.bjj_api.entities.Match;
import com.estudos.bjj_api.repositories.MatchRepository;

public class MatchService {
    private final MatchRepository matchRepository;
    public MatchService(MatchRepository matchRepository){
        this.matchRepository = matchRepository;
    }
    public List<Match> getAllMatch(){
        return matchRepository.findAll();
    }
    public Match insertMatch (Match match){
        return  matchRepository.save(match);
    }
    public Optional <Match> readById(Long id){
        return matchRepository.findById(id);
    }
    public void delMatch(Long id){
        matchRepository.deleteById(id);
    }
    public Match updateMatch(Long id, Match match){
        match.setId(id);
        return matchRepository.save(match);
    }
    
}
