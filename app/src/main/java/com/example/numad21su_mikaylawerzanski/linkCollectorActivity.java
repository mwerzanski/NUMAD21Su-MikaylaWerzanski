package com.example.numad21su_mikaylawerzanski;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Random;

public class linkCollectorActivity extends AppCompatActivity {
    private ArrayList<ItemCard> itemList = new ArrayList<>();
    private RecyclerView recyclerView;
    private RviewAdapter rviewAdapter;
    private RecyclerView.LayoutManager rLayoutManger;
    private FloatingActionButton addButton;
    private static final String KEY_OF_INSTANCE = "KEY_OF_INSTANCE";
    private static final String NUMBER_OF_ITEMS = "NUMBER_OF_ITEMS";
    private String inputItemName;
    private String inputItemLink;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link_collector);

        init(savedInstanceState);

        addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = 0;
                addItem(pos);
            }
        });

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                Toast.makeText(linkCollectorActivity.this, "Deleted link successfully", Toast.LENGTH_SHORT).show();
                int position = viewHolder.getLayoutPosition();
                itemList.remove(position);
                rviewAdapter.notifyItemRemoved(position);
            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {

        int size = itemList == null ? 0 : itemList.size();
        outState.putInt(NUMBER_OF_ITEMS, size);
        for (int i = 0; i < size; i++) {
            outState.putString(KEY_OF_INSTANCE + i + "0", itemList.get(i).getName());
            outState.putString(KEY_OF_INSTANCE + i + "1", itemList.get(i).getLink());
        }
        super.onSaveInstanceState(outState);
    }

    private void init(Bundle savedInstanceState) {
        initialItemData(savedInstanceState);
        createRecyclerView();
    }

    private void initialItemData(Bundle savedInstanceState) {

        if (savedInstanceState != null && savedInstanceState.containsKey(NUMBER_OF_ITEMS)) {
            if (itemList == null || itemList.size() == 0) {

                int size = savedInstanceState.getInt(NUMBER_OF_ITEMS);

                for (int i = 0; i < size; i++) {
                    String name = savedInstanceState.getString(KEY_OF_INSTANCE + i + "0");
                    String link = savedInstanceState.getString(KEY_OF_INSTANCE + i + "1");

                    ItemCard itemCard = new ItemCard(name, link);

                    itemList.add(itemCard);
                }
            }
        }

    }

    private void createRecyclerView() {


        rLayoutManger = new LinearLayoutManager(this);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        rviewAdapter = new RviewAdapter(itemList);
        ItemClickListener itemClickListener = new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                itemList.get(position).onItemClick(position);
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(itemList.get(position).getLink()));
                    startActivity(intent);
                } catch (Exception e) {
                    Toast.makeText(linkCollectorActivity.this, "An error occurred. " +
                            "Please make sure your link is in the correct form.", Toast.LENGTH_LONG).show();
                }

                rviewAdapter.notifyItemChanged(position);
            }
        };
        rviewAdapter.setOnItemClickListener(itemClickListener);

        recyclerView.setAdapter(rviewAdapter);
        recyclerView.setLayoutManager(rLayoutManger);
    }

    private void addItem(int position) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        final EditText inputName = new EditText(this);
        inputName.setHint("Name:");
        inputName.setInputType(InputType.TYPE_CLASS_TEXT);
        final EditText inputLink = new EditText(this);
        inputLink.setHint("Link:");
        inputLink.setInputType(InputType.TYPE_CLASS_TEXT);

        layout.addView(inputName);
        layout.addView(inputLink);
        builder.setView(layout);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                inputItemName = inputName.getText().toString();
                inputItemLink = inputLink.getText().toString();
                itemList.add(position, new ItemCard(inputItemName, inputItemLink));
                Toast.makeText(linkCollectorActivity.this, "An item was successfully added", Toast.LENGTH_SHORT).show();

                rviewAdapter.notifyItemInserted(position);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }
}