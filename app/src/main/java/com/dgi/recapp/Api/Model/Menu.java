package com.dgi.recapp.Api.Model;

import java.util.List;

public class Menu {


    private int Id;
    private String Titulo;
    private DatosTiempo Data;


    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public DatosTiempo getData() {
        return Data;
    }

    public void setData(DatosTiempo data) {
        Data = data;
    }
}


