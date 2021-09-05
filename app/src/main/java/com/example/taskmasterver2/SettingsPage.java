package com.example.taskmasterver2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SettingsPage extends AppCompatActivity {

    EditText userNameHolder;
    Button
            save,
            back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_page);
        userNameHolder = findViewById(R.id.editTextTextPersonName);
        save = findViewById(R.id.saveSettingsBtn);
        back = findViewById(R.id.goBackBtn3);
    }

    @Override
    protected void onStart() {
        super.onStart();
        save.setOnClickListener(view->{
            Toast.makeText(SettingsPage.this, "clicked!", Toast.LENGTH_SHORT).show();
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(SettingsPage.this);
            SharedPreferences.Editor sharedPreferencesEditor = sharedPreferences.edit();
            String userNameText = userNameHolder.getText().toString();
            sharedPreferencesEditor.putString("userNameText", userNameText);
            sharedPreferencesEditor.apply();
            Intent goToHomePage = new Intent(SettingsPage.this, MainActivity.class);
            startActivity(goToHomePage);
        });

        back.setOnClickListener(view->{
            Intent backHome = new Intent(SettingsPage.this, MainActivity.class);
            startActivity(backHome);
        });

    }
}