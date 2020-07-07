package com.covid19.odisha;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ProgressBar;

import com.firebase.geofire.GeoFire;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class checking extends AppCompatActivity {
String number;
ProgressBar progress;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checking);
        progress = (ProgressBar) findViewById(R.id.progressBar);
        progress.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onStart() {
        super.onStart();
       number=PreferenceManager.getDefaultSharedPreferences(checking.this).getString("Phone", "defaultStringIfNothingFound");
        DatabaseReference Affected= FirebaseDatabase.getInstance().getReference("Affected");
        Affected.orderByChild("mobile").equalTo(number).addListenerForSingleValueEvent(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue() != null){
                    new AlertDialog.Builder(checking.this)
                            .setTitle("COVID-19 POSITIVE")
                            .setMessage("You have been tested COVID-19 Positive. We will be collecting your location updates!!")
                            // Specifying a listener allows you to take an action before dismissing the dialog.
                            // The dialog is automatically dismissed when a dialog button is clicked.
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // Continue with service
                                    Intent service = new Intent(getApplicationContext(), locationupdate.class);
                                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O){

                                        checking.this.startForegroundService(service);
                                        check();
                                    }
                                    else{
                                        startService(service);
                                        check();
                                    }

                                }
                            })

                            // A null listener allows the button to dismiss the dialog and take no further action.
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                }
                else{
                    user = FirebaseAuth.getInstance().getCurrentUser();
                    String uuid=user.getUid();
                    DatabaseReference saveloc=FirebaseDatabase.getInstance().getReference("Locations");
                    GeoFire geoFire = new GeoFire(saveloc);
                    geoFire.removeLocation(uuid);
                    Intent service = new Intent(getApplicationContext(), locationupdate.class);
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O){

                        checking.this.stopService(service);
                    }
                    else{
                        stopService(service);
                    }
                    check();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void check(){

        number=PreferenceManager.getDefaultSharedPreferences(checking.this).getString("Phone", "defaultStringIfNothingFound");
        DatabaseReference DatabaseUnaffected=FirebaseDatabase.getInstance().getReference("Users");
        DatabaseUnaffected.orderByChild("mobile").equalTo(number).addListenerForSingleValueEvent(new ValueEventListener(){

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue() != null){
                    Intent intent = new Intent(getApplicationContext(), home.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }

                else{
                    Intent intent = new Intent(getApplicationContext(), register_user.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


        });

    }
}
