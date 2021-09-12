package com.example.taskmasterver2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.TaskGenerated;

public class AddTheTask extends AppCompatActivity {

    Button submit;
    EditText title, description, state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_the_task);
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
            Intent returnBackIntent = new Intent(AddTheTask.this, MainActivity.class);
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
        TaskGenerated todo = TaskGenerated.builder()
                .title(task.title)
                .body(task.body)
                .state(task.state)
                .build();

        Amplify.API.mutate(
                ModelMutation.create(todo),
                response -> Log.i("MyAmplifyApp", "Added Todo with id: " + response.getData().getId()),
                error -> Log.e("MyAmplifyApp", "Create failed", error)
        );
        finish();
    }

}