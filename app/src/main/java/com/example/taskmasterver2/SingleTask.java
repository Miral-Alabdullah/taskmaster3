package com.example.taskmasterver2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SingleTask extends AppCompatActivity {

    TextView titleOfSingleTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_task);
        titleOfSingleTask.findViewById(R.id.singleTaskTitle);

        Intent intent = getIntent();
        String getTheTitle = intent.getExtras().getString("task");
        titleOfSingleTask.setText(getTheTitle);
    }
}