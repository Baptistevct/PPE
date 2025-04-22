package com.example.ppeboutique;

import android.os.Bundle;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    //List<Vetements> lpa;

    EditText etEmail, etMdp;
    Button b;

    RequestQueue fileRequetesWs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //on cree la file d'attente de volley pour poster les requetes
        fileRequetesWs = Volley.newRequestQueue(this);
        etEmail = findViewById(R.id.email);
        etMdp = findViewById(R.id.mdp);

        Button btConnexion = findViewById(R.id.btConnexion);
        btConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString().trim();
                String password = etMdp.getText().toString().trim();

                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                    return;
                }

                requestLogin(email, password);
            }
        });
    }

    private void requestLogin(String mail, String pw){
        String url= "http://192.168.0.107/~baptiste.vincent/PPE/public/api/login";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, this::processLogin, this::gereErreursWS) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> hm = new HashMap<>();
                hm.put("email", mail);
                hm.put("password", pw);
                return hm;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/x-www-form-urlencoded");
                return headers;
            }
        };
        //depot de la demande
        fileRequetesWs.add(stringRequest);
    }
    private void processLogin(String reponse){
        try {
            JSONObject jo = new JSONObject(reponse);
            String message = jo.getString("message");
            String token = jo.getString("token");
            JSONObject user = jo.getJSONObject("User");
            int idUtilisateur = user.getInt("idUtilisateur");

            Intent i = new Intent(this, CategoriesVetements.class);
            i.putExtra("token", token);
            i.putExtra("idUtilisateur", idUtilisateur);
            startActivity(i);

            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
        catch (Exception jx){
            Toast.makeText(this,"JSON PARSE ERROR",Toast.LENGTH_LONG).show();
            Log.e("HELLOJWT",reponse);
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
