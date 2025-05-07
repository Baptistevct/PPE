package com.example.ppeboutique;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CategorieAdapter extends RecyclerView.Adapter<CategorieViewHolder>{
    private List<Categorie> mesVetements;
    public CategorieAdapter(List<Categorie> lp){
        mesVetements=lp;
    }
    ICategorieViewListener listener;

    public void setListener(ICategorieViewListener l){
        listener=l;
    }

    @NonNull
    @Override
    public CategorieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
// Inflate the custom layout
        View categoriesView = inflater.inflate(R.layout.item_categories, parent, false);
// Return a new holder instance
        CategorieViewHolder viewHolder = new CategorieViewHolder(categoriesView, listener);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull CategorieViewHolder holder,
                                 int position) {
// Get the data model based on position
        Categorie categories=mesVetements.get(position);
        holder.bindData(categories);
    }
    @Override
    public int getItemCount() {return mesVetements.size();}
}
