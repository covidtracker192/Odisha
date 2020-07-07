package com.covid19.odisha;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.geofire.GeoFire;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class mark_patient extends AppCompatActivity {
    DatabaseReference DatabaseUnaffected;
    DatabaseReference users;
    DatabaseReference DatabaseAffected;
    String id;
    TextView Number;
    TextView Name;
    TextView Age;
    TextView Gender;
    TextView Blood;
    TextView Emergency;
    TextView Condition;
    ProgressBar progressBar;
    String newname, newage, newgender, newblood, newemergency, newcondition;
    String Mobile;
    ImageView positive;
    ImageView healthy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark_patient);
        Mobile=getIntent().getStringExtra("phonenumber2");
        Number=(TextView) findViewById(R.id.textView48);
        Number.setText(Mobile);
        Name = (TextView) findViewById(R.id.textView47);
        Age = (TextView) findViewById(R.id.textView46);
        Gender = (TextView) findViewById(R.id.textView45);
        Blood = (TextView) findViewById(R.id.textView43);
        Emergency= (TextView) findViewById(R.id.textView42);
        Condition = (TextView) findViewById(R.id.textView41);
        progressBar = (ProgressBar) findViewById(R.id.progressBar7);
        progressBar.setVisibility(View.VISIBLE);
        healthy= findViewById(R.id.imageView27);
        positive = findViewById(R.id.imageView28);
        users= FirebaseDatabase.getInstance().getReference("Users");
        users.orderByChild("mobile").equalTo(Mobile).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChildren()) {
                    for (DataSnapshot datas : dataSnapshot.getChildren()) {
                        id =datas.child("id").getValue().toString();
                        String name = datas.child("name").getValue().toString();
                        Name.setText(name);
                        newname = name;
                        String age = datas.child("age").getValue().toString();
                        Age.setText(age);
                        newage = age;
                        String gender = datas.child("sex").getValue().toString();
                        Gender.setText(gender);
                        newgender = gender;
                        String blood = datas.child("blood").getValue().toString();
                        Blood.setText(blood);
                        newblood = blood;
                        String emergency = datas.child("emergency").getValue().toString();
                        Emergency.setText(emergency);
                        newemergency = emergency;
                        String condition = datas.child("condition").getValue().toString();
                        newcondition = condition;
                        Condition.setText(condition);
                    }
                    progressBar.setVisibility(View.GONE);
                }
                else{
                    Toast.makeText(mark_patient.this, "No Patient Found", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //checking if user is positive or healthy
        DatabaseUnaffected=FirebaseDatabase.getInstance().getReference("Unaffected");
        DatabaseUnaffected.orderByChild("mobile").equalTo(Mobile).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue() != null){
                    positive.setVisibility(View.VISIBLE);
                }

                else {
                    healthy.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //healthy to positive

        positive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(mark_patient.this)
                        .setTitle("Mark Positive")
                        .setMessage("Are you sure you want mark this patient COVID-19 Positive?")

                        // Specifying a listener allows you to take an action before dismissing the dialog.
                        // The dialog is automatically dismissed when a dialog button is clicked.
                        .setPositiveButton("Mark Positive", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                DatabaseReference DatabaseUnaffected= FirebaseDatabase.getInstance().getReference("Unaffected");
                                DatabaseUnaffected.orderByChild("mobile").equalTo(Mobile).addListenerForSingleValueEvent(new ValueEventListener() {
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

                                addUser();

                            }
                        })

                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton("Cancel", null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();

            }
        });

        // positive to healthy

        healthy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(mark_patient.this)
                        .setTitle("Mark Negative")
                        .setMessage("Are you sure you want to mark this patient COVID-19 Negative?")

                        // Specifying a listener allows you to take an action before dismissing the dialog.
                        // The dialog is automatically dismissed when a dialog button is clicked.
                        .setPositiveButton("Mark Negative", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                DatabaseAffected= FirebaseDatabase.getInstance().getReference("Affected");
                                DatabaseAffected.orderByChild("mobile").equalTo(Mobile).addListenerForSingleValueEvent(new ValueEventListener() {
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
                                DatabaseReference saveloc=FirebaseDatabase.getInstance().getReference("Locations");
                                GeoFire geoFire = new GeoFire(saveloc);
                                geoFire.removeLocation(id);

                                addUser2();

                            }
                        })

                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton("Cancel", null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });
    }
    //if user is marked positive
    private void addUser(){
        DatabaseAffected= FirebaseDatabase.getInstance().getReference().child("Affected");
        if (!TextUtils.isEmpty(newname) && !TextUtils.isEmpty(newage) && !TextUtils.isEmpty(newgender) && !TextUtils.isEmpty(newblood) && !TextUtils.isEmpty(newemergency) && !TextUtils.isEmpty(newcondition)) {
            unaffected User =new unaffected(id, Mobile, newname, newage, newgender, newblood, newemergency, newcondition);
            DatabaseAffected.child(id).setValue(User);
            Toast.makeText(this, "Patient Details Updated", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(mark_patient.this, confirm.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }

    }
    // if user is marked healthy
    private void addUser2(){
        DatabaseUnaffected= FirebaseDatabase.getInstance().getReference("Unaffected");
        if (!TextUtils.isEmpty(newname) && !TextUtils.isEmpty(newage) && !TextUtils.isEmpty(newgender) && !TextUtils.isEmpty(newblood) && !TextUtils.isEmpty(newemergency) && !TextUtils.isEmpty(newcondition)) {
            unaffected User =new unaffected(id, Mobile, newname, newage, newgender, newblood, newemergency, newcondition);
            DatabaseUnaffected.child(id).setValue(User);
            Toast.makeText(this, "Patient Details Updated", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(mark_patient.this, confirm.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }
    public void onBackPressed()
    {
        Intent intent = new Intent(mark_patient.this, patient_num.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    };
}
