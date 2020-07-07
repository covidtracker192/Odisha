package com.covid19.odisha;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class settings extends AppCompatActivity {
ImageView positivebut;
ImageView negativebut;
ImageView update;
ProgressBar progressBar;
Switch theme;
ImageView about;
    FirebaseUser user;
    String number; @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
     positivebut=findViewById(R.id.imageView13);
        negativebut=findViewById(R.id.imageView16);
        progressBar=findViewById(R.id.progressBar3);
        update=findViewById(R.id.imageView14);
        progressBar.setVisibility(View.VISIBLE);
        theme=findViewById(R.id.switch1);
        about=findViewById(R.id.imageView);
        number= PreferenceManager.getDefaultSharedPreferences(settings.this).getString("Phone", "defaultStringIfNothingFound");

        DatabaseReference Affected= FirebaseDatabase.getInstance().getReference("Unaffected");
        Affected.orderByChild("mobile").equalTo(number).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue() != null){
                    negativebut.setVisibility(View.VISIBLE);
                    update.setVisibility(View.VISIBLE);
                    about.setVisibility(View.VISIBLE);
                    theme.setVisibility(View.VISIBLE);
                }

                else {
                    positivebut.setVisibility(View.VISIBLE);
                    update.setVisibility(View.VISIBLE);
                    about.setVisibility(View.VISIBLE);
                    theme.setVisibility(View.VISIBLE);

                }
                progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });


        negativebut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //public Dialog onCreateDialog(Bundle savedInstanceState){
                new AlertDialog.Builder(settings.this)
                        .setTitle("Delete Profile")
                        .setMessage("Are you sure you want to delete your profile?")

                        // Specifying a listener allows you to take an action before dismissing the dialog.
                        // The dialog is automatically dismissed when a dialog button is clicked.
                        .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Continue with delete

                                DatabaseReference DatabaseUnaffected= FirebaseDatabase.getInstance().getReference("Unaffected");
                                DatabaseUnaffected.orderByChild("mobile").equalTo(number).addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        for(DataSnapshot datas: dataSnapshot.getChildren()){
                                            datas.getRef().removeValue();
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });
                                //User DB
                                DatabaseReference User= FirebaseDatabase.getInstance().getReference("Users");
                                User.orderByChild("mobile").equalTo(number).addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        for(DataSnapshot datas: dataSnapshot.getChildren()){
                                            datas.getRef().removeValue();
                                            Toast.makeText(settings.this, "User Deleted", Toast.LENGTH_LONG).show();
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }

                                });
                                Intent service = new Intent(getApplicationContext(), locationupdate.class);
                                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O){

                                    settings.this.stopService(service);
                                }
                                else{
                                    stopService(service);
                                }
                                Intent intent = new Intent(settings.this, global_login.class);
                                startActivity(intent);
                                finish();

                            }
                        })

                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton("Cancel", null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();

            }

        });

        positivebut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(settings.this)
                        .setTitle("COVID-19 Positive")
                        .setMessage("You are a positive patient!! Profile can't be deleted.")

                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton("Ok", null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(settings.this, update_profile.class);
                startActivity(intent);
                finish();
                return;
            }
        });

        int nightModeFlags = this.getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        switch (nightModeFlags) {
            case Configuration.UI_MODE_NIGHT_YES:
               theme.setChecked(true);
                break;

            case Configuration.UI_MODE_NIGHT_NO:
                theme.setChecked(false);
                break;
        }

        theme.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    recreate();
                }
                else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    recreate();
                }
            }
        });

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(settings.this, about.class);
                startActivity(intent);
                finish();
                return;
            }
        });
    }
    public void onBackPressed()
    {
        Intent intent = new Intent(settings.this, home.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    };

}
