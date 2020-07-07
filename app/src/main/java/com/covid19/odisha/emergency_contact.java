package com.covid19.odisha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class emergency_contact extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_contact);

        TextView nhn;
        nhn=findViewById(R.id.textView36);
        nhn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:+911123978046"));
                startActivity(intent);
            }
        });

        TextView ghn;
        ghn=findViewById(R.id.textView39);
        ghn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:+91104"));
                startActivity(intent);
            }
        });

        TextView fhnn;
        fhnn=findViewById(R.id.textView43);
        fhnn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:+919423890077"));
                startActivity(intent);
            }
        });

        TextView fhns;
        fhns=findViewById(R.id.textView45);
        fhns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:+919423890066"));
                startActivity(intent);
            }
        });

        TextView web;
        web=findViewById(R.id.textView47);
        web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uriUrl = Uri.parse("https://www.mohfw.gov.in");
                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);
            }
        });

        TextView mail;
        mail=findViewById(R.id.textView41);
        mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                Uri data = Uri.parse("mailto:"
                        + "ncov2019@gov.in"
                        + "?subject=" + "" + "&body=" + "");
                intent.setData(data);
                startActivity(intent);
            }
        });
    }

    public void onBackPressed(){
        //stopService(new Intent(getApplicationContext(), Location_Services.class));
        Intent intent = new Intent(emergency_contact.this, home.class);
        startActivity(intent);
        finish();
    }
}
