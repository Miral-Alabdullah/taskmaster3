package com.example.taskmasterver2;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddTask extends AppCompatActivity {

    TextView taskTitle;
    TextView taskDescription;
    Button goBack;
    Button submit;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addtask);
        taskTitle = findViewById(R.id.editTextTaskTitle);
        taskDescription = findViewById(R.id.editTextTaskDescription);
        goBack = findViewById(R.id.goBackBtn);
        submit = findViewById(R.id.addATaskBtn);
    }

    @Override
    public void onStart() {
        super.onStart();
        submit.setOnClickListener(view -> Toast.makeText(this, "Submitted", Toast.LENGTH_SHORT).show());
        goBack.setOnClickListener(view -> {
            Intent returnBackIntent = new Intent(AddTask.this, MainActivity.class);
            startActivity(returnBackIntent);
        });
    }



}