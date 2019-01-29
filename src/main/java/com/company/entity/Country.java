/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.entity;

/**
 *
 * @author TURAL
 */
public class Country {
    Integer id;
    String name; 
    String natinality;

    public Country(Integer id, String name, String natinality) {
        this.id = id;
        this.name = name;
        this.natinality = natinality;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNatinality() {
        return natinality;
    }

    public void setNatinality(String natinality) {
        this.natinality = natinality;
    }

    @Override
    public String toString() {
        return "" + name + "(" + natinality + ')';
    }
    
}
