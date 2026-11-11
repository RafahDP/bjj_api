package com.estudos.bjj_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.estudos.bjj_api.entities.Match;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {

}
