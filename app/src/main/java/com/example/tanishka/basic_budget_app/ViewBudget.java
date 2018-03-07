package com.example.tanishka.basic_budget_app;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ViewBudget extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_budget);
        Intent intent = getIntent();
        String account_balance = intent.getStringExtra(UpdateBalance.BALANCE);


        final TextView balanceTextView = (TextView) findViewById(R.id.bal);
        balanceTextView.setText(account_balance);

        final EditText  editIncome = (EditText) findViewById(R.id.income_input);
        final EditText  editExpense = (EditText) findViewById(R.id.expense_input);

        final TextView spendingMoney = (TextView) findViewById(R.id.budget);
        Button calculateSpendingMoney = (Button) findViewById(R.id.calculate_budget);

        calculateSpendingMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double balance = Double.parseDouble(balanceTextView.getText().toString());
                Double income = Double.parseDouble(editIncome.getText().toString());;
                Double expenses = Double.parseDouble(editExpense.getText().toString());;;

                Double moneyToSpend = balance + income - expenses;

                if (moneyToSpend >= 0){
                    spendingMoney.setText(moneyToSpend.toString());
                    spendingMoney.setTextColor(Color.GREEN);
                } else {
                    spendingMoney.setText(moneyToSpend.toString());
                    spendingMoney.setTextColor(Color.RED);
                }




            }
        });
    }
}
