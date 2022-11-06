package com.school.response;

import lombok.*;


public class SchoolResponse {

    private String name;

    private String dateFoundation;

    private String adress;

    private String city;

    private String codePostal;

    private String director;

    public SchoolResponse() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateFoundation() {
        return dateFoundation;
    }

    public void setDateFoundation(String dateFoundation) {
        this.dateFoundation = dateFoundation;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }
}
