package com.example.ppeboutique;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ModeleViewHolder extends RecyclerView.ViewHolder {
    TextView typVet;
    TextView modele;
    TextView prix;

    public ModeleViewHolder(View itemView, IModeleViewListener listener) {
        super(itemView);
        typVet = itemView.findViewById(R.id.typVet);
        modele = itemView.findViewById(R.id.modele);
        prix = itemView.findViewById(R.id.prix);

        typVet.setOnClickListener(p -> {
            if (listener != null) {
                listener.onProduitClicked(getAdapterPosition());
            }
        });
    }

    public void bindData(Modele p) {
        // vider les champs dans l'instance de produit
        typVet.setText(p.getTypVet());
        modele.setText(p.getModele());
        prix.setText(""+p.getPrix());
    }
}