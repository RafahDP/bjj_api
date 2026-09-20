package com.estudos.bjj_api.entities;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Table(name = "match_tb")
@Entity 
public class Match{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne 
    @JoinColumn (name = "athlete1_id")
    private Athlete athlete1;
    @ManyToOne 
    @JoinColumn (name = "athlete2_id")
    private Athlete athlete2;

    @ManyToOne
    @JoinColumn (name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn (name = "winner_id")
    private Athlete winner;

    private String matchStatus;

    public Match(){}
    public void setAthlete1(Athlete athlete1){
        this.athlete1 = athlete1;
    }
    public Athlete getAthlete1(){
        return athlete1;
    }
    public void setAthlete2(Athlete athlete2){
        this.athlete2 = athlete2;
    }
    public Athlete getAthlete2(){
        return athlete2;
    }
    public void setCategory (Category category){
        this.category = category;
    }
    public Category getCategory(){
        return category;
    }
    public void setWinner(Athlete winner){
        this.winner = winner;
    }
    public Athlete getWinner(){
        return winner;
    }
    public void setMatchStatus(String matchStatus){
        this.matchStatus = matchStatus;
    }
    public String getMatchStatus(){
        return matchStatus;
    }
}