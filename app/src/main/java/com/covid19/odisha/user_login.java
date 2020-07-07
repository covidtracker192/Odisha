package com.covid19.odisha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

public class user_login extends AppCompatActivity {
EditText phone;
ImageView but;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        phone=findViewById(R.id.editText);
        but=findViewById(R.id.imageView4);

        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = phone.getText().toString().trim();

                if (number.isEmpty() || number.length() < 10) {
                    phone.setError("Valid number is required");
                    phone.requestFocus();
                    return;
                }
                PreferenceManager.getDefaultSharedPreferences(user_login.this).edit().putString("Phone",number).apply();
                Intent intent = new Intent(user_login.this, verify_phone.class);
                intent.putExtra("phonenumber", number);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            Intent intent = new Intent(this, checking.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }
}
