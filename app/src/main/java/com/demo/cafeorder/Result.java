package com.demo.cafeorder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Result extends AppCompatActivity {
    private TextView textViewFullOrder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        textViewFullOrder = findViewById(R.id.textViewFullOrder);
        Intent intent = getIntent();
        if (intent.hasExtra("order")) {
            String order = intent.getStringExtra("order");
            textViewFullOrder.setText(order);
        } else {
            Intent backToLogin = new Intent(this, MainActivity.class);
            startActivity(backToLogin);
        }
    }
}