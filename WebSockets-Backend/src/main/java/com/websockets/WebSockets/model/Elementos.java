package com.websockets.WebSockets.model;

public class Elementos {

    public String id;
    public String nombre;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Elementos(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

}