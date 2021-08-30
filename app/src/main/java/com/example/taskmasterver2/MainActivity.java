package com.example.taskmasterver2;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button toAddTask;
    Button toAllTasks;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(getApplicationContext(), "onCreate()", Toast.LENGTH_SHORT).show();
        toAddTask = findViewById(R.id.toAddTaskBtn);
        toAllTasks = findViewById(R.id.toSeeTheTasksBtn);

    }

    @Override
    public void onStart() {
        super.onStart();
        Toast.makeText(getApplicationContext(), "onStart()", Toast.LENGTH_SHORT).show();
        toAddTask.setOnClickListener(view -> {
            Intent firstIntent = new Intent(MainActivity.this, AddTask.class);
            startActivity(firstIntent);
            Toast.makeText(MainActivity.this, "This button was clicked", Toast.LENGTH_SHORT).show();
        });
        toAllTasks.setOnClickListener(view -> {
            Toast.makeText(MainActivity.this, "This button was clicked", Toast.LENGTH_SHORT).show();
                Intent toAllTasksIntent = new Intent(MainActivity.this, AllTasks.class);
                startActivity(toAllTasksIntent);
        });
    }


}