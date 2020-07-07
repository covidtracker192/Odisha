package com.covid19.odisha;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class home extends AppCompatActivity {
Button profile, settings, volunteer, alert, pmcare, goafund, hospital, contact, preventive;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        profile=findViewById(R.id.button2);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home.this, profile.class);
                startActivity(intent);
                finish();
            }
        });
        settings=findViewById(R.id.button5);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home.this, settings.class);
                startActivity(intent);
                finish();
            }
        });
        volunteer=findViewById(R.id.button6);
        volunteer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uriUrl = Uri.parse("https://www.mygov.in/task/join-war-against-covid-19-register-volunteer/");
                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);
            }
        });
        alert=findViewById(R.id.button);
        alert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home.this, UsersMapsActivity.class);
                startActivity(intent);
                finish();
            }
        });
        hospital=findViewById(R.id.button3);
        hospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home.this, hospitals.class);
                startActivity(intent);
                finish();
            }
        });
        contact=findViewById(R.id.button4);
        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home.this, emergency_contact.class);
                startActivity(intent);
                finish();
            }
        });
        preventive=findViewById(R.id.button15);
        preventive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home.this, preventive_measure.class);
                startActivity(intent);
                finish();
            }
        });
        pmcare=findViewById(R.id.button10);
        pmcare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(home.this)
                        .setTitle("PM CARES FUND")
                        .setMessage("You will be redirected to the official website of PM CARES Fund. Do you want to continue?")
                        .setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Continue with pmcares
                                Uri uriUrl = Uri.parse("https://www.pmcares.gov.in/en/");
                                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                                startActivity(launchBrowser);
                            }
                        })

                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton("Cancel", null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });
        goafund=findViewById(R.id.button9);
        goafund.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(home.this)
                        .setTitle("Goa State COVID-19 Relief Fund.")
                        .setMessage("You will be redirected to the official payment link of Goa State COVID-19 Relief Fund. Do you want to continue?")
                        .setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Continue with COVID-19 goa relief fund
                                Uri uriUrl = Uri.parse("https://www.onlinesbi.com/sbicollect/icollecthome.htm?corpID=1963359");
                                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                                startActivity(launchBrowser);
                            }
                        })

                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton("Cancel", null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });
    }
    public void onBackPressed(){
        //stopService(new Intent(getApplicationContext(), Location_Services.class));
        Intent intent = new Intent(home.this, global_login.class);
        startActivity(intent);
        finish();
    }
}
