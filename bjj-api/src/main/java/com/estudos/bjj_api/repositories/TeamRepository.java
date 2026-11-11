package com.estudos.bjj_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.estudos.bjj_api.entities.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

}
