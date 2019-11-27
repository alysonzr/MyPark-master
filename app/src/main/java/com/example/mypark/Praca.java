package com.example.mypark;

import com.google.firebase.firestore.DocumentReference;

import java.util.ArrayList;
import java.util.List;

public class Praca {

    private String uid;
    private String nome;
    private String facilidades;
    private String endereco;
    private ArrayList imagem;
    private String icon;


    public Praca(String uid, String nome, String facilidades, String endereco, ArrayList imagem,String icon) {
        this.uid = uid;
        this.nome = nome;
        this.facilidades = facilidades;
        this.endereco = endereco;
        this.imagem = imagem;
        this.icon = icon;

    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public ArrayList getImagem() {
        return imagem;
    }

    public void setImagem(ArrayList imagem) {
        this.imagem = imagem;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public String getFacilidades() {
        return facilidades;
    }

    public void setFacilidades(String facilidades) {
        this.facilidades = facilidades;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
