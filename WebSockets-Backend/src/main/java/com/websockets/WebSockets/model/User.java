package com.websockets.WebSockets.model;

public class User{

    public String cc;
    public String name;

    public String getCc() {
        return this.cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User(String cc, String name) {
        this.cc = cc;
        this.name = name;
    }

}