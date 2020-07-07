package com.covid19.odisha;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class password_change extends AppCompatActivity {
TextView email;
EditText oldpass;
EditText password;
EditText confirmpass;
ImageView confirm;
ProgressBar progressBar;
    String id;
    String newpass, pass;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_change);
        progressBar=findViewById(R.id.progressBar8);
        confirm=findViewById(R.id.imageView32);
        email=findViewById(R.id.textView33);
        oldpass=findViewById(R.id.editText20);
        password=findViewById(R.id.editText18);
        confirmpass=findViewById(R.id.editText19);
        id = PreferenceManager.getDefaultSharedPreferences(password_change.this).getString("ID", "defaultStringIfNothingFound");
        email.setText(id);
        newpass= confirmpass.getText().toString();
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(password_change.this)
                        .setTitle("Update Password")
                        .setMessage("Are you sure you want to update your password?")
                        .setPositiveButton("Update", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                pass=password.getText().toString();
                                newpass= confirmpass.getText().toString();
                                if(newpass.equals(pass)){
                                    changepass();
                                }
                                else{
                                    Toast.makeText(getApplicationContext(), "New password and confirm password didn't match!!", Toast.LENGTH_LONG).show();
                                }
                            }
                        })

                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton("Cancel", null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });
    }

    private void changepass(){
        progressBar.setVisibility(View.VISIBLE);
        String old=oldpass.getText().toString();
        newpass= confirmpass.getText().toString();
        user = FirebaseAuth.getInstance().getCurrentUser();
        AuthCredential credential = EmailAuthProvider.getCredential(id, old);
        user.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    user.updatePassword(newpass).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(getApplicationContext(), "Something went wrong. Please try again later", Toast.LENGTH_LONG).show();
                            }
                            else {
                                Toast.makeText(getApplicationContext(), "Password Successfully Updated", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(password_change.this, worker_home.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            }
                        }
                    });
                }
                else {
                    Toast.makeText(getApplicationContext(), "Authentication Failed", Toast.LENGTH_LONG).show();
                }
                progressBar.setVisibility(View.INVISIBLE);
            }
        });
    }

    public void onBackPressed(){
        Intent intent = new Intent(password_change.this, worker_settings.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
