package com.example.ppeboutique;

public class Modele {
    public String typVet;
    public String modele;
    public Double prix;

    public Modele(String typVet, String modele, Double prix) {
        this.typVet = typVet;
        this.modele = modele;
        this.prix = prix;
    }

    public String getTypVet() {
        return typVet;
    }

    public void setTypVet(String typVet) {
        this.typVet = typVet;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }
}
