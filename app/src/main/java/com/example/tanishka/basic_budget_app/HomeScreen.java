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
            }
        });
    }


    // helper methods to build all intents for different screens
    public void toViewBudget(){
        // intent for view budget screen
        Intent viewBudgetScreen = new Intent(this, ViewBudget.class);
        startActivity(viewBudgetScreen);
    }







}
