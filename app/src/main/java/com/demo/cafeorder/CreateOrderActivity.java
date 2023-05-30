package com.demo.cafeorder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

public class CreateOrderActivity extends AppCompatActivity {
    private TextView textViewHello;
    private TextView textViewAddition;
    private CheckBox checkBoxMilk;
    private CheckBox checkBoxSugar;
    private CheckBox checkBoxLemon;
    private Spinner spinnerCoffee;
    private Spinner spinnerTea;
    private String name;
    private String drink;

    StringBuilder select_additions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_order);
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        String password = intent.getStringExtra("password");
        textViewHello = findViewById(R.id.textViewHello);
        String hello = String.format(getString(R.string.text_hello), name);
        textViewHello.setText(hello);
        drink = getString(R.string.coffee);
        textViewAddition = findViewById(R.id.textViewAddition);
        String addition = String.format(getString(R.string.ask_addition), drink);
        textViewAddition.setText(addition);
        checkBoxMilk = findViewById(R.id.checkBoxMilk);
        checkBoxSugar = findViewById(R.id.checkBoxSugar);
        checkBoxLemon = findViewById(R.id.checkBoxLemon);
        spinnerCoffee = findViewById(R.id.spinnerCoffee);
        spinnerTea = findViewById(R.id.spinnerTea);
        select_additions = new StringBuilder();
    }

    public void onClickChangeDrink(View view) {
        RadioButton button = (RadioButton) view;
        int id = button.getId();
        if (id == R.id.radioButtonTea) {
            drink = getString(R.string.tea);
            checkBoxLemon.setVisibility(View.VISIBLE);
            spinnerTea.setVisibility(View.VISIBLE);
            spinnerCoffee.setVisibility(View.INVISIBLE);
        } else if (id == R.id.radioButtonCoffee) {
            drink = getString(R.string.coffee);
            checkBoxLemon.setVisibility(View.INVISIBLE);
            spinnerCoffee.setVisibility(View.VISIBLE);
            spinnerTea.setVisibility(View.INVISIBLE);
        }
        String text_addition = String.format(getString(R.string.ask_addition), drink);
        textViewAddition.setText(text_addition);
    }
    public void onClickMakeOrder(View view) {
        select_additions.setLength(0);
        if (checkBoxMilk.isChecked()) {
            select_additions.append(getString(R.string.milk) + "м ");
        }
        if (checkBoxSugar.isChecked()) {
            select_additions.append(getString(R.string.sugar) + "ом ");
        }
        if (checkBoxLemon.isChecked() && spinnerTea.getVisibility() == View.VISIBLE) {
            select_additions.append(getString(R.string.lemon) + "ом ");
        }

        String type;
        if (spinnerTea.getVisibility() == View.VISIBLE) {
            type = spinnerTea.getSelectedItem().toString();
        } else {
            type = spinnerCoffee.getSelectedItem().toString();
        }
        String order;
        if (spinnerTea.getVisibility() == View.VISIBLE && select_additions.length() > 0) {
           order = String.format(getString(R.string.result_tea) ,name, type, drink, select_additions);
        } else if (spinnerCoffee.getVisibility() == View.VISIBLE && select_additions.length() > 0) {
            order = String.format(getString(R.string.result_coffee),name, type, select_additions);
        } else if (spinnerTea.getVisibility() == View.VISIBLE) {
            order = String.format(getString(R.string.result_tea_without_additions) ,name, type, drink);
        } else {
            order = String.format(getString(R.string.result_coffee_without_additions) ,name, type);
        }
        Intent intent = new Intent(this, Result.class);
        intent.putExtra("order", order);
        startActivity(intent);
    }
}