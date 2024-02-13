package com.elitcoder.teacherassistant.Attendance;

public class StudentModel {
    int img;
    String stdId;
    String stdName;

    public StudentModel(int img, String stdId, String stdName) {
        this.img = img;
        this.stdId = stdId;
        this.stdName = stdName;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getStdId() {
        return stdId;
    }

    public void setStdId(String stdId) {
        this.stdId = stdId;
    }

    public String getStdName() {
        return stdName;
    }

    public void setStdName(String stdName) {
        this.stdName = stdName;
    }
}
