package com.example.numad21su_mikaylawerzanski;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class RviewHolder extends RecyclerView.ViewHolder {
    public TextView name;
    public TextView link;

    public RviewHolder(View itemView, final ItemClickListener listener) {
        super(itemView);
        name = itemView.findViewById(R.id.item_name);
        link = itemView.findViewById(R.id.item_link);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    int position = getLayoutPosition();
                    if (position != RecyclerView.NO_POSITION) {

                        listener.onItemClick(position);
                    }
                }
            }
        });
    }
}