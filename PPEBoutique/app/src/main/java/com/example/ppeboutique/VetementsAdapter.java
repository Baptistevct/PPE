package com.example.ppeboutique;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class VetementsAdapter extends RecyclerView.Adapter<VetementsViewHolder>{
    private List<Vetements> mesVetements;
    public VetementsAdapter(List<Vetements> lp){
        mesVetements=lp;
    }
    IVetementsViewListener listener;

    public void setListener(IVetementsViewListener l){
        listener=l;
    }

    @NonNull
    @Override
    public VetementsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
// Inflate the custom layout
        View categoriesView = inflater.inflate(R.layout.item_categories, parent, false);
// Return a new holder instance
        VetementsViewHolder viewHolder = new VetementsViewHolder(categoriesView, listener);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull VetementsViewHolder holder,
                                 int position) {
// Get the data model based on position
        Vetements categories=mesVetements.get(position);
        holder.bindData(categories);
    }
    @Override
    public int getItemCount() {return mesVetements.size();}
}
