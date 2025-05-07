package com.example.ppeboutique;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class CategorieViewHolder extends RecyclerView.ViewHolder {
    Button type;

    public CategorieViewHolder(View itemView, ICategorieViewListener listener) {
        super(itemView);
        type = itemView.findViewById(R.id.type);

        type.setOnClickListener(v -> {
            if (listener != null) {
                listener.onVetementClicked(new Categorie(type.getText().toString()));
            }
        });
    }

    public void bindData(Categorie v) {
        type.setText(v.getType());
    }
}