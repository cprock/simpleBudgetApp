package com.example.tanishka.basic_budget_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class HomeScreen extends AppCompatActivity {

    protected Button viewBudget; // to see what money is available to spend (i.e. not on bills)
    protected Button fromGoogle; // import excel spreadsheet from Google drive
    protected Button toGoogle; // export into an excel spreadsheet on Google drive
    protected Button updateBal; // to edit biweekly salary or current balance
    protected Button webView; // to go to Amazon and see what is available for purchase


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        Intent intent = getIntent();

        viewBudget = (Button) findViewById(R.id.viewBudget);
        viewBudget.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                // call method to go to view budget screen
                toViewBudget();
            }
        });

        fromGoogle = (Button) findViewById(R.id.fromGoogle);
        fromGoogle.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                // call method to go to import from Google
                toImportGoogle();
            }
        });

        toGoogle = (Button) findViewById(R.id.toGoogle);
        toGoogle.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                // call method to go to export to Google
                toExportGoogle();
            }
        });

        updateBal = (Button) findViewById(R.id.updateBal);
        updateBal.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                // call method to update balance screen
                updateBalance();
            }
        });
    }


    // helper methods to build all intents for different screens

    public void toViewBudget(){
        // intent for view budget screen
        Intent viewBudgetScreen = new Intent(this, ViewBudget.class);
        startActivity(viewBudgetScreen);
    }


    public void toImportGoogle(){
        // intent for importing from Google screen
        Intent toImportGoogle = new Intent(this, ImportGoogle.class);
        startActivity(toImportGoogle);
    }


    public void toExportGoogle(){
        // intent for exporting to Google screen
        Intent toExportGoogle = new Intent(this, ExportGoogle.class);
        startActivity(toExportGoogle);
    }

    public void updateBalance(){
        // intent for editing balance and/or salary
        Intent toUpdateBal = new Intent(this, UpdateBalance.class);
        startActivity(toUpdateBal);
    }




}
