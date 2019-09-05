package com.dgi.recapp.Api.Model;

public class Menu {



    private int Id;
    private String Titulo;
    private String Descripcion;
    private String ImageIcon;


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

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getIamgenIcon() {
        return ImageIcon;
    }

    public void setIamgenIcon(String iamgenIcon) {
        ImageIcon = iamgenIcon;
    }



}
