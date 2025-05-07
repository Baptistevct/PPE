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

public class ListeModeleActivity extends AppCompatActivity {
    //file d'attente web services
    RequestQueue fileRequetesWS;
    List<Modele> lp;
    @Override


    /*
        creation de l'activity
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_liste_vetements);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //je recupere l'intent qui m'a créé
        Intent i2 = getIntent();
        String type = i2.getStringExtra("type");
        Log.i("VETEMENTS","Categorie parcourue: "+type);
        fileRequetesWS= Volley.newRequestQueue(this);
        requestVetementsParCategorie(type);
    }

    /**
     * Methode qui envoie la requete de recuperation de vetements par categories
     */
    public void requestVetementsParCategorie(String type){
        String url= "http://192.168.0.107/~baptiste.vincent/PPE/public/api/getVetementParCategories/"+type;
        StringRequest stringRequest;
        stringRequest = new StringRequest(Request.Method.GET,url,this::processVetementParCategories,this::gereErreursWS);
        //depot de la demande
        fileRequetesWS.add(stringRequest);

    }

    /**
     * Methode qui traite la reponse de la requete de recup des categories
     * @param reponse la reponse renvoyee par volley
     */

    public void processVetementParCategories(String reponse){
        try {
            JSONArray results = new JSONArray(reponse);

            lp = new ArrayList<>();
            Log.i("VETEMENTS","rep http: "+reponse);
            for (int i = 0; i < results.length(); i++) {
                JSONObject record = results.getJSONObject(i);
                String typVet = record.getString("typeVetement"); // ← Vérifie si c'est "typeVetement" ou juste "type"
                String modele = record.getString("modele");
                //String taille = record.getString("taille");
                //Double prix = record.getDouble("prix");
                lp.add(new Modele(typVet, modele, -1d));
                Log.i("VETEMENTS","Item ajouté");
            }

            Toast.makeText(this, "Données trouvées", Toast.LENGTH_LONG).show();



            ModeleAdapter adapter = new ModeleAdapter(lp);
            adapter.setListener(new IModeleViewListener() {
                @Override
                public void onProduitClicked(int posModele) {
                    Modele p=lp.get(posModele);
                    Toast.makeText(ListeModeleActivity.this, "Clique sur : " + p.getTypVet(), Toast.LENGTH_SHORT).show();
                }
            });

            RecyclerView rvListe = findViewById(R.id.rvListe);
            Log.i("VETEMENTS","RV récupérée");
            rvListe.setAdapter(adapter);
            Log.i("VETEMENTS","Adapter ajouté");
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
