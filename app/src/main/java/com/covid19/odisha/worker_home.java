package com.covid19.odisha;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class worker_home extends AppCompatActivity {
TextView mail;
Button forums, mark, change;
Button forums1, mark1, change1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_home);
        mail=findViewById(R.id.textView15);
        String email = PreferenceManager.getDefaultSharedPreferences(worker_home.this).getString("ID", "defaultStringIfNothingFound");
        String s1 = email.substring(0, email.lastIndexOf("@"));
         mail.setText(s1);
        mail.setTextColor(Color.parseColor("#000080"));
        mail.setGravity(Gravity.CENTER);
        Intent service = new Intent(getApplicationContext(), locationupdate.class);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O){

            worker_home.this.stopService(service);
        }
        else{
            stopService(service);
        }

        forums=findViewById(R.id.button7);
        forums.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(worker_home.this, Forums.class);
                startActivity(intent);
                finish();
            }
        });
        forums1=findViewById(R.id.button12);
        forums1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(worker_home.this, Forums.class);
                startActivity(intent);
                finish();
            }
        });

        mark=findViewById(R.id.button8);
        mark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(worker_home.this, patient_num.class);
                startActivity(intent);
                finish();
            }
        });
        mark1=findViewById(R.id.button11);
        mark1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(worker_home.this, patient_num.class);
                startActivity(intent);
                finish();
            }
        });

        change=findViewById(R.id.button13);
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(worker_home.this, worker_settings.class);
                startActivity(intent);
                finish();
            }
        });
        change1=findViewById(R.id.button14);
        change1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(worker_home.this, worker_settings.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void onBackPressed()
    {
        //public Dialog onCreateDialog(Bundle savedInstanceState){
        new AlertDialog.Builder(this)
                .setTitle("Exit")
                .setMessage("Are you sure you want to exit?")

                // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton("Signout", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Continue with exit
                        FirebaseAuth.getInstance().signOut();
                        Intent intent = new Intent(worker_home.this, global_login.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                })

                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton("Cancel", null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

    }
    };
