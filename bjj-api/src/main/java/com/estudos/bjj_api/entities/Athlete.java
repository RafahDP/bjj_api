package com.estudos.bjj_api.entities;


import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


@Entity
@Table (name = "athlete_tb")
public class Athlete{
    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank(message = "O nome do atleta não pode estar em branco")
    private String name;
    @ManyToOne
    @JoinColumn(name = "team_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Team team;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @NotNull(message = "A idade é obrigatória")
    @Positive(message = "A idade deve ser um valor positivo")
    private int age;
    private String gender;
    @NotNull(message = "O peso é obrigatório")
    @Positive(message = "O peso deve ser um valor positivo")
    private float weight;
    @NotBlank(message = "A graduação (belt) é obrigatória")
    private String belt;
    private Boolean isFighting;
    
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

    public void setIsFighting(Boolean isFighting){
        this.isFighting = isFighting;
    }
    public Boolean getIsFighting(){
        return this.isFighting;
    }
   

}
