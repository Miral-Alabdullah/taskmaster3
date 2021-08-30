package com.example.taskmasterver2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AllTasks extends AppCompatActivity {

    Button goBack;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alltasks);
        goBack = findViewById(R.id.goBackBtn);
    }

    @Override
    public void onStart() {
        super.onStart();
        goBack.setOnClickListener(view -> {
            Intent returnBackIntent = new Intent(AllTasks.this, MainActivity.class);
            startActivity(returnBackIntent);

        });
    }
}
