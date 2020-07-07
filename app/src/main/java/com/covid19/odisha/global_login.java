package com.covid19.odisha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class global_login extends AppCompatActivity {
Button user, worker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_global_login);
        user=findViewById(R.id.button17);
        worker=findViewById(R.id.button16);
        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isOnline()) {
                    Intent intent = new Intent(global_login.this, user_login.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(global_login.this, "Check your Internet Connection!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        worker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isOnline()) {
                    Intent intent = new Intent(global_login.this, worker_login.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(global_login.this, "Check your Internet Connection!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }
    public void onBackPressed(){
       finish();
    }

}
