package com.example.taskmasterver2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class TaskDetailPage extends AppCompatActivity {

    TextView title;
    Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail_page);
        title = findViewById(R.id.titleView);
        back = findViewById(R.id.goBackBtn2);
    }

    @Override
    protected void onStart() {
        super.onStart();
        back.setOnClickListener(view->{
            Intent backHome = new Intent(TaskDetailPage.this, MainActivity.class);
            startActivity(backHome);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        String getTheTitle = intent.getExtras().getString("titlePass");
        title.setText(getTheTitle);
    }
}