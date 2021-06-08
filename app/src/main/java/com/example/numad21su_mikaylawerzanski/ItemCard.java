package com.example.numad21su_mikaylawerzanski;

import android.content.Intent;
import android.net.Uri;

public class ItemCard implements ItemClickListener {

    private final String name;
    private final String link;

    //Constructor
    public ItemCard(String name, String link) {
        this.name = name;
        this.link = link;
    }

    //Getters for item name and link
    public String getName() {
        return name;
    }

    public String getLink() {
        return link;
    }

    @Override
    public void onItemClick(int position) {

    }
}