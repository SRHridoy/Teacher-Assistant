package com.elitcoder.teacherassistant.ShortNotes;


public class Topic {
    private String title;
    private String description;

    //Default Constructor :
    public Topic() {
    }
    //Parameterized Constructor :
    public Topic(String title, String description) {
        this.title = title;
        this.description = description;
    }

    //Getter and setters :

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getdescription() {
        return description;
    }

    public void setdescription(String description) {
        this.description = description;
    }
}
