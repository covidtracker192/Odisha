package com.covid19.odisha;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;

public class worker_settings extends AppCompatActivity {
ImageView update;
ImageView about;
Switch theme;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_settings);
        update=findViewById(R.id.imageView35);
        theme=findViewById(R.id.switch3);
        about=findViewById(R.id.imageView36);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(worker_settings.this, password_change.class);
                startActivity(intent);
                finish();
                return;
            }
        });

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(worker_settings.this, about_worker.class);
                startActivity(intent);
                finish();
                return;
            }
        });

        int nightModeFlags = this.getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        switch (nightModeFlags) {
            case Configuration.UI_MODE_NIGHT_YES:
                theme.setChecked(true);
                break;

            case Configuration.UI_MODE_NIGHT_NO:
                theme.setChecked(false);
                break;
        }

        theme.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    recreate();
                }
                else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    recreate();
                }
            }
        });
    }

    public void onBackPressed(){
        Intent intent = new Intent(worker_settings.this, worker_home.class);
        startActivity(intent);
        finish();
        return;

    };
}
