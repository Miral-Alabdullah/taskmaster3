package com.example.taskmasterver2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.TaskGenerated;
import com.amplifyframework.datastore.generated.model.Team;

public class AddTheTask extends AppCompatActivity {

    Button submit,
            back,
            uploadFile;
    EditText title, description, state;
    RadioButton teamFreeWill, ackreman, unagi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_the_task);
        submit = findViewById(R.id.addATaskBtn);
        title = findViewById(R.id.editTextTaskTitle);
        description = findViewById(R.id.editTextTaskDescription);
        state = findViewById(R.id.editTextTaskState);
        back = findViewById(R.id.goBack);
        uploadFile = findViewById(R.id.uploadFileBtn);
        teamFreeWill = findViewById(R.id.radioTeamFreeWill);
        ackreman = findViewById(R.id.radioAckerman);
        unagi = findViewById(R.id.radioUnagi);


//        saveTeamData("TeamFreeWill");
//        saveTeamData("Ackreman");
//        saveTeamData("Unagi");


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

        back.setOnClickListener(view -> {
            Intent returnBackIntent = new Intent(AddTheTask.this, MainActivity.class);
            startActivity(returnBackIntent);
        });

        uploadFile.setOnClickListener(this::uploadingTheFile);
    }

    private void uploadingTheFile(View view) {
        Intent chooseFile = new Intent(Intent.ACTION_GET_CONTENT);
        chooseFile.setType("*/*");
        chooseFile = Intent.createChooser(chooseFile, "Upload A File");
        startActivityForResult(chooseFile, 510);
    }



    private void saveTheData(String title, String description, String state){
        TaskGenerated todo = TaskGenerated.builder()
                .title(title)
                .body(description)
                .state(state)
                .build();

        Amplify.API.mutate(
                ModelMutation.create(todo),
                response -> Log.i("MyAmplifyApp", "Added Todo with id: " + response.getData().getId()),
                error -> Log.e("MyAmplifyApp", "Create failed", error)
        );
        finish();
    }

    private void saveTeamData(String name){
        Team team = Team.builder()
                .name(name)
                .build();

        Amplify.API.mutate(
                ModelMutation.create(team),
                response -> Log.i("MyAmplifyApp", "Added Todo with id: " + response.getData().getId()),
                error -> Log.e("MyAmplifyApp", "Create failed", error)
        );
        finish();
    }

}