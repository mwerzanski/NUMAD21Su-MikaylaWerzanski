package com.example.numad21su_mikaylawerzanski;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RviewAdapter extends RecyclerView.Adapter<RviewHolder> {

    private final ArrayList<ItemCard> itemList;
    private ItemClickListener listener;

    //Constructor
    public RviewAdapter(ArrayList<ItemCard> itemList) {
        this.itemList = itemList;
    }

    public void setOnItemClickListener(ItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public RviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
        return new RviewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(RviewHolder holder, int position) {
        ItemCard currentItem = itemList.get(position);

        holder.name.setText(currentItem.getName());
        holder.link.setText(currentItem.getLink());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}
