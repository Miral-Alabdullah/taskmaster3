package com.example.taskmasterver2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.TaskGenerated;
import com.amplifyframework.datastore.generated.model.Team;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class AddTheTask extends AppCompatActivity {

    Button submit,
            back,
            uploadFile;
    EditText title, description, state;
    RadioButton teamFreeWill, ackreman, unagi;
    Team team = null;
    String name = "name placeholder";
    List<Team> teams = new ArrayList<>();
    String imageName= "";

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
        getTeamData();
    }


    @Override
    public void onStart() {
        super.onStart();
        submit.setOnClickListener(view -> {
            Toast.makeText(this, "Submitted", Toast.LENGTH_SHORT).show();
            onRadioButtonClicked(view);
            saveTheData(title.getText().toString(), description.getText().toString(),
                    state.getText().toString());
            System.out.println(title.getText().toString());
            System.out.println("***************************");
            System.out.println(description.getText().toString());
            System.out.println("***************************");
            System.out.println(state.getText().toString());
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




    @SuppressLint("NonConstantResourceId")
    public void onRadioButtonClicked(View view) {
        if(teamFreeWill.isChecked()){
            name = "TeamFreeWill";
            System.out.println(name + "***************************");
        } else if(ackreman.isChecked()){
            name = "Ackreman";
            System.out.println(name);
        } else if(unagi.isChecked()){
            name = "Unagi";
            System.out.println(name);
        }
        for(int i = 0; i<teams.size(); i++){
            if (teams.get(i).getName().equals(name)){
                team = teams.get(i);
            }
        }

    }



    private void saveTheData(String title, String description, String state){
        TaskGenerated todo = TaskGenerated.builder()
                .title(title)
                .body(description)
                .state(state)
                .team(team)
                .image(imageName)
                .build();

        Amplify.API.mutate(
                ModelMutation.create(todo),
                response -> Log.i("MyAmplifyApp", "Added Todo with id: " + response.getData().getId()),
                error -> Log.e("MyAmplifyApp", "Create failed", error)
        );
        finish();
    }


    private void getTeamData(){
        Amplify.API.query(
                ModelQuery.list(Team.class),
                response -> {
                    for (Team team : response.getData()) {
                        Log.i("MyAmplifyApp", team.getName());
                        teams.add(team);
                    }
                },
                error -> Log.e("MyAmplifyApp", "Query failure", error)
        );
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try {
            InputStream exampleInputStream = getContentResolver().openInputStream(data.getData());
            imageName = data.getData().toString();

            Amplify.Storage.uploadInputStream(
                    imageName,
                    exampleInputStream,
                    result -> Log.i("MyAmplifyApp", "Successfully uploaded: " + result.getKey()),
                    storageFailure -> Log.e("MyAmplifyApp", "Upload failed", storageFailure)
            );
        }  catch (FileNotFoundException error) {
            Log.e("MyAmplifyApp", "Could not find file to open for input stream.", error);
        }
    }
}
