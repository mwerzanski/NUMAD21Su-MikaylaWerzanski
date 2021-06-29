package com.example.numad21su_mikaylawerzanski;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button aboutBtn = (Button) findViewById(R.id.aboutBtn);
        aboutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Mikayla Werzanski \n" +
                        "Contact: werzanski.m@northeastern.edu", Toast.LENGTH_LONG).show();
            }
        });

        Button clickyClicky = findViewById(R.id.clickyClicky);
        clickyClicky.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("Clicky Clicky screen appears");

                Intent clickyActivity = new Intent(getApplicationContext(), clickyActivity.class);
                startActivity(clickyActivity);
            }
        });

        Button linkCollector = findViewById(R.id.linkCollector);
        linkCollector.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("Link Collector screen appears");

                Intent linkCollectorActivity = new Intent(getApplicationContext(), linkCollectorActivity.class);
                startActivity(linkCollectorActivity);
            }
        });

        Button locator = findViewById(R.id.locator);
        locator.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("Locator screen appears");

                Intent locatorActivity = new Intent(getApplicationContext(), LocatorActivity.class);
                startActivity(locatorActivity);
            }
        });

        Button webService = findViewById(R.id.webService);
        webService.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("Web service screen appears");

                Intent webService = new Intent(getApplicationContext(), WebService.class);
                startActivity(webService);
            }
        });
    }
}