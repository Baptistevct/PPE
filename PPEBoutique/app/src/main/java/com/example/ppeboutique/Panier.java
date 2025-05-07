package com.example.ppeboutique;

import java.util.HashMap;
import java.util.Map;

public class Panier {
    //variable statique de memorisation de l'instance
    private static Panier instance = null;
    //Dictionnaire de stockage du contenue
    private Map<String, Integer> contenu;

    //constructeur privé
    private Panier() {
        contenu = new HashMap<>();
    }

    //méthode publique permettant d'obtenir l'unique panier
    public static Panier getInstance() {
        if (null == instance) {
            instance = new Panier();
            return instance;
        } else {
            return instance;
        }

    }

    //vide le panier
    public void vider() {
        //instance.clear();
    }

    //ajoute une qt d'un certain produit au panier
    public void ajoute(String ref, int qt) {
        //instance.put("clef", valeur);
        instance.getInstance();
        //on vérifie si la clef existe, si elle n'existe pas alors on l'ajoute au panier
        if (ref == null){
            //instance.put("", 1);
        }
        //Sinon si la clef existe deja alors on ajoute +1 a la quantité
        else if (ref != null){
            //instance.put(ref, +1);
        }
    }

    //enleve une qt d'un produit au panier
    public void enleve(String ref, int qt){
        //instance.put("", ) pour Override la valeur d'une clef
        //On vérifie si la clef existe puis on enlève 1 a la quantité, si la quantité=0 alors on supprime le produit du panier
    }

    //serialise le panier en JSON pour envoi au panier
    public String toJSON(){
        //Displaying key value pairs using for loop
        //for(String i : instance.keySet()){

        //}
        return "";
    }

}
