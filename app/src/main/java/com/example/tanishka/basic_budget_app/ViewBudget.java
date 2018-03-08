package com.example.tanishka.basic_budget_app;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ViewBudget extends AppCompatActivity {

    private String finalBudget;
    public static final String intent_message = "BUDGET";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_budget);

        //getActionBar().setDisplayHomeAsUpEnabled(true);


        final EditText balanceTextView = (EditText) findViewById(R.id.balance);

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

                finalBudget = "Balance: " + Double.toString(balance) + "\n"
                        + "Income: " + Double.toString(income) + "\n"
                        + "Expenses: " + Double.toString(expenses) + "\n"
                        + "Disposable Income: " + Double.toString(moneyToSpend);
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                //NavUtils.navigateUpFromSameTask(this);
                Intent intent = new Intent(this, HomeScreen.class);
                intent.putExtra(intent_message, finalBudget);
                startActivity(intent);

                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
