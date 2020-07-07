package com.covid19.odisha;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class profile extends AppCompatActivity {
    TextView Number;
    TextView Name;
    TextView Age;
    TextView Gender;
    TextView Blood;
    TextView Emergency;
    TextView Condition;
    ProgressBar progressBar;
    ImageView positive;
    ImageView negative;
    String uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        String number = PreferenceManager.getDefaultSharedPreferences(profile.this).getString("Phone", "defaultStringIfNothingFound");
        Number=findViewById(R.id.textView16);
        Number.setText(number);
        Name=findViewById(R.id.textView17);
        Age=findViewById(R.id.textView18);
        Gender=findViewById(R.id.textView23);
        Blood=findViewById(R.id.textView19);
        Emergency=findViewById(R.id.textView20);
        Condition=findViewById(R.id.textView21);
        progressBar=findViewById(R.id.progressBar2);
        positive=findViewById(R.id.imageView10);
        negative=findViewById(R.id.imageView11);
        progressBar.setVisibility(View.VISIBLE);
        DatabaseReference DatabaseUnaffected= FirebaseDatabase.getInstance().getReference("Users");
        DatabaseUnaffected.orderByChild("mobile").equalTo(number).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot datas: dataSnapshot.getChildren()){
                    String name = datas.child("name").getValue().toString();
                    Name.setText(name);
                    String age = datas.child("age").getValue().toString();
                    Age.setText(age);
                    String gender = datas.child("sex").getValue().toString();
                    Gender.setText(gender);
                    String blood = datas.child("blood").getValue().toString();
                    Blood.setText(blood);
                    String emergency = datas.child("emergency").getValue().toString();
                    Emergency.setText(emergency);
                    String condition = datas.child("condition").getValue().toString();
                    Condition.setText(condition);
                }
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        DatabaseReference Affected=FirebaseDatabase.getInstance().getReference("Unaffected");
        Affected.orderByChild("mobile").equalTo(number).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue() != null){
                    negative.setVisibility(View.VISIBLE);
                }

                else {
                    positive.setVisibility(View.VISIBLE);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void onBackPressed()
    {
        Intent intent = new Intent(profile.this, home.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    };
}
