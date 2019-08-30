package com.dgi.recapp.Api.Model;

public class FichaModel {

    public String getRuc() {
        return Ruc;
    }

    public void setRuc(String ruc) {
        Ruc = ruc;
    }

    public String getNombreComercial() {
        return NombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        NombreComercial = nombreComercial;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }

    public String getFechaFicha() {
        return FechaFicha;
    }

    public void setFechaFicha(String fechaFicha) {
        FechaFicha = fechaFicha;
    }

    private String Ruc;
    private String NombreComercial;
    private String Telefono;
    private String FechaFicha;
}
