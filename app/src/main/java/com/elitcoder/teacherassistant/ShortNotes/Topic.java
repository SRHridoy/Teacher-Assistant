package com.elitcoder.teacherassistant.ShortNotes;


public class Topic {
    private String title;
    private String keypoints;

    //Default Constructor :
    public Topic() {
    }
    //Parameterized Constructor :
    public Topic(String title, String keypoints) {
        this.title = title;
        this.keypoints = keypoints;
    }

    //Getter and setters :

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKeypoints() {
        return keypoints;
    }

    public void setKeypoints(String keypoints) {
        this.keypoints = keypoints;
    }
}
