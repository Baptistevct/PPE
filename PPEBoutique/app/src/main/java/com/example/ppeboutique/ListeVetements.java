package com.example.ppeboutique;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ListeVetements extends AppCompatActivity {
    //file d'attente web services
    RequestQueue fileRequetesWS;
    List<Vetements> lp;
    @Override


    /*
        creation de l'activity
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_categories_vetements);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //je recupere l'intent qui m'a créé
        //Intent i = getIntent();

        fileRequetesWS= Volley.newRequestQueue(this);
        requestCategories();
    }

    /**
     * Methode qui envoie la requete de recuperation des categories
     */
    public void requestCategories(){
        String url= "http://192.168.0.107/~baptiste.vincent/PPE/public/api/getVetementParCategoriesId/";
        StringRequest stringRequest;
        stringRequest = new StringRequest(Request.Method.GET,url,this::processCategories,this::gereErreursWS);
        //depot de la demande
        fileRequetesWS.add(stringRequest);

    }

    /**
     * Methode qui traite la reponse de la requete de recup des categories
     * @param reponse la reponse renvoyee par volley
     */

    public void processCategories(String reponse){
        try {
            JSONArray results = new JSONArray(reponse);

            lp = new ArrayList<>();

            for (int i = 0; i < results.length(); i++) {
                JSONObject record = results.getJSONObject(i);
                String type = record.getString("model"); 
                lp.add(new Vetements(type));
            }

            Toast.makeText(this, "Données trouvées", Toast.LENGTH_LONG).show();

            RecyclerView rvListe = findViewById(R.id.rvListe);

            VetementsAdapter adapter = new VetementsAdapter(lp);
            adapter.setListener(new IVetementsViewListener() {
                @Override
                public void onVetementClicked(Vetements v) {
                    Toast.makeText(ListeVetements.this, "Clique sur : " + v.getType(), Toast.LENGTH_SHORT).show();
                }
            });

            rvListe.setAdapter(adapter);
            rvListe.setLayoutManager(new LinearLayoutManager(this));
        }
        catch (JSONException jx){
            gereErreursWS(jx);
        }
    }
    public void gereErreursWS(Throwable t){
        //on prévient l'utilisateur
        Toast.makeText(this,
                "Problème de communication avec le serveur",
                Toast.LENGTH_LONG).show();
        //on log le problème
        Log.e("App","Problème de communication avec le serveur",t);
    }

}
