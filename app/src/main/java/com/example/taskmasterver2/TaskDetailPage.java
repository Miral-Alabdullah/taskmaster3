package com.example.taskmasterver2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.amplifyframework.core.Amplify;

import java.io.File;

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
        String getTheTitleFromTheRecyclerVie = intent.getExtras().getString("taskTitle");
        title.setText(getTheTitleFromTheRecyclerVie);
        title.setText(getTheTitle);
        Amplify.Storage.downloadFile(
                intent.getExtras().getString("taskImage"),
                new File(getApplicationContext().getFilesDir() + "/download.jpg"),
                result -> {
                    ImageView imageView = findViewById(R.id.imageView);
                    String taskImage = result.getFile().getPath();
                    imageView.setImageBitmap(BitmapFactory.decodeFile(taskImage));
                    Log.i("MyAmplifyApp", "Successfully downloaded: " + result.getFile());
                    },
                error -> Log.e("MyAmplifyApp",  "Download Failure", error)
        );
    }
}