package com.estudos.bjj_api.repositories;

import com.estudos.bjj_api.entities.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchRepository extends JpaRepository <Match, Long>{

}