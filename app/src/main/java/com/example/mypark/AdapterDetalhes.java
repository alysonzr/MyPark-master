package com.example.mypark;

public class AdapterDetalhes {

    public String nomeFacilidades;
    public String icon;

    public AdapterDetalhes(String nomeFacilidades, String icon) {
        this.nomeFacilidades = nomeFacilidades;
        this.icon = icon;
    }

    public String getNomeFacilidades() {
        return nomeFacilidades;
    }

    public void setNomeFacilidades(String nomeFacilidades) {
        this.nomeFacilidades = nomeFacilidades;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
