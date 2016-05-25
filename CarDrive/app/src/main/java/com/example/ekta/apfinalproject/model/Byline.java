package com.example.ekta.apfinalproject.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Byline {

    @SerializedName("person")
    @Expose
    private List<com.example.ekta.apfinalproject.model.Person> person = new ArrayList<com.example.ekta.apfinalproject.model.Person>();
    @SerializedName("original")
    @Expose
    private String original;

    /**
     * @return The person
     */
    public List<com.example.ekta.apfinalproject.model.Person> getPerson() {
        return person;
    }

    /**
     * @param person The person
     */
    public void setPerson(List<com.example.ekta.apfinalproject.model.Person> person) {
        this.person = person;
    }

    /**
     * @return The original
     */
    public String getOriginal() {
        return original;
    }

    /**
     * @param original The original
     */
    public void setOriginal(String original) {
        this.original = original;
    }

}
