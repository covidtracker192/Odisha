package com.covid19.odisha;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Forums extends AppCompatActivity {
    TextView result;
    ProgressBar prog;
    DatabaseReference forum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forums);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Forums.this, post_forum.class);
                startActivity(intent);
                finish();
            }
        });
        prog=findViewById(R.id.progressBar6);
        prog.setVisibility(View.VISIBLE);
        result=findViewById(R.id.result);
        forum= FirebaseDatabase.getInstance().getReference("FORUM");
        forum.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    for (DataSnapshot datas : dataSnapshot.getChildren()) {
                        String email=datas.child("email").getValue().toString();
                        result.append("This was posted by: "+ email + "\n");
                        String date=datas.child("date").getValue().toString();
                        String time=datas.child("time").getValue().toString();;
                        result.append("Posted on: "+ date + " " + time +"\n" +"\n");
                        String age = datas.child("age").getValue().toString();
                        result.append("Age: "+ age + "\n");
                        String gender = datas.child("gender").getValue().toString();
                        result.append("Gender: "+ gender + "\n");
                        String symptoms = datas.child("symptoms").getValue().toString();
                        result.append("Symptoms: " +symptoms+ "\n");
                        String condition = datas.child("condition").getValue().toString();
                        result.append("Underlying Medical Conditions: " + condition + "\n");
                        String treatment=datas.child("treatment").getValue().toString();
                        result.append("Treatment Plan: " + treatment + "\n");
                        String con=datas.child("con").getValue().toString();
                        result.append("Response to Treatment: " + con + "\n");
                        String info=datas.child("info").getValue().toString();
                        result.append("Other Information: " + info + "\n");
                        result.append("\n" + "\n" +"\n" + "\n");
                    }
                    prog.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void onBackPressed(){
        Intent intent = new Intent(Forums.this, worker_home.class);
        startActivity(intent);
        finish();
    }
}
