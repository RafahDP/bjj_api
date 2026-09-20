package com.estudos.bjj_api.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table (name = "athlete_tb")
public class Athlete{
    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    private int age;
    private String gender;
    private float weight;
    private String belt;
    private String isFighting;
    
    //construtors , getters and setters.
    public Athlete(){}
    public Long getId(){
       return id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public void setName (String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }

    public void setTeam (Team team){
        this.team = team;
    }
    public Team getTeam(){
        return this.team;
    }

    public void setAge (int age){
        this.age = age;
    }
    public int getAge(){
        return this.age;
    }
    public void setWeight (float weight){
        this.weight = weight;
    }
    public float getWeight(){
        return this.weight;
    }
    public void setBelt (String belt){
        this.belt = belt;
    }
    public String getBelt(){
        return this.belt;
    }
    public void setGender (String gender){
        this.gender = gender;
    }
    public String getGender(){
        return this.gender;
    }
    public void setCategory(Category category){
        this.category = category;
    }
    public Category getCategory(){
        return this.category;
    }

    public void setIsFighting(String isFighting){
        this.isFighting = isFighting;
    }
    public String getIsFighting(){
        return this.isFighting;
    }
   

}
