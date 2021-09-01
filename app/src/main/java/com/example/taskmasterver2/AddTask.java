package com.example.taskmasterver2;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddTask extends AppCompatActivity {

    Button goBack;
    Button submit;
    EditText title, description, state;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addtask);
        goBack = findViewById(R.id.goBackBtn);
        submit = findViewById(R.id.addATaskBtn);
        title = findViewById(R.id.editTextTaskTitle);
        description = findViewById(R.id.editTextTaskDescription);
        state = findViewById(R.id.editTextTaskState);
    }

    @Override
    public void onStart() {
        super.onStart();
        submit.setOnClickListener(view -> {
            Toast.makeText(this, "Submitted", Toast.LENGTH_SHORT).show();
            saveTheData(title.getText().toString(), description.getText().toString(),
                         state.getText().toString());
        });
        goBack.setOnClickListener(view -> {
            Intent returnBackIntent = new Intent(AddTask.this, MainActivity.class);
            startActivity(returnBackIntent);
        });
    }
    private void saveTheData(String title, String description, String state){
        AppDatabase appDatabase =  AppDatabase.getTheInstance(this.getApplicationContext());
        Task task = new Task();
        task.title = title;
        task.body = description;
        task.state = state;
        appDatabase.taskDao().insertTask(task);

        finish();
    }



}

