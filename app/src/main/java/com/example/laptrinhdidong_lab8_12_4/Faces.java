package com.example.laptrinhdidong_lab8_12_4;

public class Faces {
    private  int id;
    private String email;
    private int happy;
    private int sad;
    private int normal;

    public Faces() {
    }

    public Faces(String email) {
        this.email = email;
    }

    public Faces(String email, int happy) {
        this.email = email;
        this.happy = happy;
    }

    public Faces(int id, String email, int happy, int sad, int normal) {
        this.id = id;
        this.email = email;
        this.happy = happy;
        this.sad = sad;
        this.normal = normal;
    }

    public Faces(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getHappy() {
        return happy;
    }

    public void setHappy(int happy) {
        this.happy = happy;
    }

    public int getSad() {
        return sad;
    }

    public void setSad(int sad) {
        this.sad = sad;
    }

    public int getNormal() {
        return normal;
    }

    public void setNormal(int normal) {
        this.normal = normal;
    }
}
