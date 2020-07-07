package com.covid19.odisha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class patient_num extends AppCompatActivity {
EditText number;
ImageView fetch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_num);

        number=findViewById(R.id.editText17);
        fetch=findViewById(R.id.imageView25);
        fetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mobile = number.getText().toString().trim();
                if (mobile.isEmpty() || number.length() < 10) {
                    number.setError("Valid number is required");
                    number.requestFocus();
                    return;
                }
                Intent intent = new Intent(patient_num.this, mark_patient.class);
                intent.putExtra("phonenumber2", mobile);
                startActivity(intent);
            }
        });
    }
    public void onBackPressed()
    {
        Intent intent = new Intent(patient_num.this, worker_home.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    };
}
