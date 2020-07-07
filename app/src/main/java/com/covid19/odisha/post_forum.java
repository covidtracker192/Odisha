package com.covid19.odisha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class post_forum extends AppCompatActivity {
    EditText age;
    EditText sex;
    EditText symptoms;
    EditText medical_cond;
    EditText treatment;
    RadioGroup condition;
    EditText info;
    ImageView post;
    String con;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_forum);
        post=findViewById(R.id.imageView23);
        age=findViewById(R.id.editText14);
        sex=findViewById(R.id.editText15);
        symptoms=findViewById(R.id.editText16);
        medical_cond=findViewById(R.id.editText12);
        treatment=findViewById(R.id.editText11);
        info=findViewById(R.id.editText13);
        condition=findViewById(R.id.radioGroup);

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Age = age.getText().toString();
                String Sex = sex.getText().toString();
                String Symptoms = symptoms.getText().toString();
                String Condition = medical_cond.getText().toString();
                String Treatment = treatment.getText().toString();
                String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
                String email = PreferenceManager.getDefaultSharedPreferences(post_forum.this).getString("ID", "defaultStringIfNothingFound");
                int selectedRadioButtonID = condition.getCheckedRadioButtonId();
                RadioButton selectedRadioButton = findViewById(selectedRadioButtonID);
                con = selectedRadioButton.getText().toString();
                String Info=info.getText().toString();
                String id = FirebaseAuth.getInstance().getCurrentUser().getUid();
                DatabaseReference forum= FirebaseDatabase.getInstance().getReference("FORUM").child(id);
                forumdb data= new forumdb(id, Age, Sex, Symptoms, Condition, Treatment, con, Info, currentDate, currentTime, email);
                forum.setValue(data);
                Toast.makeText(post_forum.this, "Forum Posted", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(post_forum.this, Forums.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }
    public void onBackPressed()
    {
        Intent intent = new Intent(post_forum.this, Forums.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    };
}
