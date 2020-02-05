package com.websockets.WebSockets.model;

public class Notifications {

    private int count;
    private String name;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Notifications(int count, String name) {
        this.count = count;
        this.name = name;
    }

    public Notifications(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void increment() {
        this.count++;
    }
    
}
