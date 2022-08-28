package com.example.shram;

public class Job {

    private int image;
    private String jname;

    public Job(){

    }

    public Job(int image, String jname) {
        this.image = image;
        this.jname = jname;
    }

    public int getImage() {
        return image;
    }

    public String getJname() {
        return jname;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setJname(String jname) {
        this.jname = jname;
    }
}
