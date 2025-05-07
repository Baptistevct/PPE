package com.example.ppeboutique;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ModeleAdapter extends RecyclerView.Adapter<ModeleViewHolder>{
    private List<Modele> mesVetements;
    public ModeleAdapter(List<Modele> lp){
        mesVetements=lp;
    }
    IModeleViewListener listener;

    public void setListener(IModeleViewListener l){
        listener=l;
    }

    @NonNull
    @Override
    public ModeleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
// Inflate the custom layout
        View modeleView = inflater.inflate(R.layout.item_liste, parent, false);
// Return a new holder instance
        ModeleViewHolder viewHolder = new ModeleViewHolder(modeleView, listener);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull ModeleViewHolder holder,
                                 int position) {
// Get the data model based on position
        Modele modele=mesVetements.get(position);
        holder.bindData(modele);
    }
    @Override
    public int getItemCount() {return mesVetements.size();}
}
