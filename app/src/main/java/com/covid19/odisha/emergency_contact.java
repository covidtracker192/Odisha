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
        TextView ohn;
        ohn=findViewById(R.id.textView44);
        ohn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel: 9439994859"));
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
