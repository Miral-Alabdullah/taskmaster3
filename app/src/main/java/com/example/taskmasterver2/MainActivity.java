package com.example.taskmasterver2;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button
            toAddTask,
            toAllTasks,
            settings,
            problemSolving,
            criticalThinking,
            timeManagement;

    TextView userNameText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(getApplicationContext(), "onCreate()", Toast.LENGTH_SHORT).show();
        toAddTask = findViewById(R.id.toAddTaskBtn);
        toAllTasks = findViewById(R.id.toSeeTheTasksBtn);
        settings = findViewById(R.id.settingsBtn);
        problemSolving = findViewById(R.id.solvingBtn);
        criticalThinking = findViewById(R.id.criticalBtn);
        timeManagement = findViewById(R.id.timeBtn);
        userNameText = findViewById(R.id.userNameHome);
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
        settings.setOnClickListener(view -> {
            Toast.makeText(MainActivity.this, "This button was clicked", Toast.LENGTH_SHORT).show();
            Intent gotToSettings = new Intent(MainActivity.this, SettingsPage.class);
            startActivity(gotToSettings);
        });
        problemSolving.setOnClickListener(view->{
            Toast.makeText(MainActivity.this, "This button was clicked", Toast.LENGTH_SHORT).show();
            String title = problemSolving.getText().toString();
            Intent goToTaskDetailPage = new Intent(MainActivity.this, TaskDetailPage.class);
            goToTaskDetailPage.putExtra("titlePass", title);
            startActivity(goToTaskDetailPage);
        });
        criticalThinking.setOnClickListener(view->{
            Toast.makeText(MainActivity.this, "This button was clicked", Toast.LENGTH_SHORT).show();
            String title = criticalThinking.getText().toString();
            Intent goToTaskDetailPage = new Intent(MainActivity.this, TaskDetailPage.class);
            goToTaskDetailPage.putExtra("titlePass", title);
            startActivity(goToTaskDetailPage);
        });
        timeManagement.setOnClickListener(view->{
            Toast.makeText(MainActivity.this, "This button was clicked", Toast.LENGTH_SHORT).show();
            String title = timeManagement.getText().toString();
            Intent goToTaskDetailPage = new Intent(MainActivity.this, TaskDetailPage.class);
            goToTaskDetailPage.putExtra("titlePass", title);
            startActivity(goToTaskDetailPage);
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        String userName = sharedPreferences.getString("userNameText", "");
        userNameText.setText(userName);
    }
}