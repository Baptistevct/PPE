package com.example.ppeboutique;

import android.view.View;
import android.widget.Button;
import androidx.recyclerview.widget.RecyclerView;

public class VetementsViewHolder extends RecyclerView.ViewHolder {
    Button type;

    public VetementsViewHolder(View itemView, IVetementsViewListener listener) {
        super(itemView);
        type = itemView.findViewById(R.id.type);

        type.setOnClickListener(v -> {
            if (listener != null) {
                listener.onVetementClicked(new Vetements(type.getText().toString()));
            }
        });
    }

    public void bindData(Vetements v) {
        type.setText(v.getType());
    }
}