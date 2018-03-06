package com.example.tanishka.basic_budget_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class HomeScreen extends AppCompatActivity {

    protected Button viewBudget; // to see what money is available to spend (i.e. not on bills)
    protected Button importFromGoogle; // import excel spreadsheet from Google drive
    protected Button exportToGoogle; // export into an excel spreadsheet on Google drive
    protected Button editAccountInfo; // to edit biweekly salary or current balance
    protected Button toWebView; // to go to Amazon and see what is available for purchase


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

        importFromGoogle = (Button) findViewById(R.id.fromGoogle);
        importFromGoogle.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                // call method to go to import from Google
                toImportGoogle();
            }
        });

        exportToGoogle = (Button) findViewById(R.id.toGoogle);
        exportToGoogle.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                // call method to go to export to Google
                toExportGoogle();
            }
        });

        editAccountInfo = (Button) findViewById(R.id.updateBal);
        editAccountInfo.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                // call method to update balance screen
                updateBalance();
            }
        });

        toWebView = (Button) findViewById(R.id.webView);
        toWebView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                // call method to go to webview
                toWebViewActivity();
            }
        });
    }


    // helper methods to build all intents for different screens

    private void toViewBudget(){
        // intent for view budget screen
        Intent viewBudgetScreen = new Intent(this, ViewBudget.class);
        startActivity(viewBudgetScreen);
    }


    private void toImportGoogle(){
        // intent for importing from Google screen
        Intent toImportGoogle = new Intent(this, ImportGoogle.class);
        startActivity(toImportGoogle);
    }


    private void toExportGoogle(){
        // intent for exporting to Google screen
        Intent toExportGoogle = new Intent(this, ExportGoogle.class);
        startActivity(toExportGoogle);
    }

    private void updateBalance(){
        // intent for editing balance and/or salary
        Intent toUpdateBal = new Intent(this, UpdateBalance.class);
        startActivity(toUpdateBal);
    }

    private void toWebViewActivity(){
        // intent for editing balance and/or salary
        Intent toWebViewActivity = new Intent(this, AmazonActivity.class);
        startActivity(toWebViewActivity);
    }




}
