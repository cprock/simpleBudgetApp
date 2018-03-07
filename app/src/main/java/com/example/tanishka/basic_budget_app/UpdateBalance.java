package com.example.tanishka.basic_budget_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class UpdateBalance extends AppCompatActivity {

    private EditText inputBalance;
    private double accountBalance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_balance);

        inputBalance = (EditText) findViewById(R.id.balance);
        accountBalance = Double.parseDouble(inputBalance.getText().toString());



    }
}
