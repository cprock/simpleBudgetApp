package com.example.tanishka.basic_budget_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ViewBudget extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_budget);
        Intent intent = getIntent();
        String account_balance = intent.getStringExtra(UpdateBalance.BALANCE);

        Double balance = Double.parseDouble(account_balance);
    }
}
