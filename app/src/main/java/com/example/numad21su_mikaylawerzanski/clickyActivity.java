package com.example.numad21su_mikaylawerzanski;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class clickyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clicky);

    }

    public void onClick(View view) {
        TextView textView = (TextView) findViewById(R.id.pressed_text);
        switch (view.getId()) {
            case R.id.B:
                textView.setText("Pressed: B");
                break;
            case R.id.A:
                textView.setText("Pressed: A");
                break;
            case R.id.C:
                textView.setText("Pressed: C");
                break;
            case R.id.D:
                textView.setText("Pressed: D");
                break;
            case R.id.E:
                textView.setText("Pressed: E");
                break;
            case R.id.F:
                textView.setText("Pressed: F");
                break;

        }
    }


    //use onclick switch statements and cases...Event Handling video demo
}