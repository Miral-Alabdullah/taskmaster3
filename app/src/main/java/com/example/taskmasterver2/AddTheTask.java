package com.example.taskmasterver2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.TaskGenerated;
import com.amplifyframework.datastore.generated.model.Team;

import java.util.ArrayList;
import java.util.List;

public class AddTheTask extends AppCompatActivity {

    Button submit, back;
    EditText title, description, state;
    RadioButton teamFreeWill, ackreman, unagi;
    Team team = null;
    String name = "name placeholder";
    List<Team> teams = new ArrayList<>();
    RadioGroup groupOfRadios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_the_task);
        submit = findViewById(R.id.addATaskBtn);
        title = findViewById(R.id.editTextTaskTitle);
        description = findViewById(R.id.editTextTaskDescription);
        state = findViewById(R.id.editTextTaskState);
        back = findViewById(R.id.goBack);

        teamFreeWill = findViewById(R.id.radioTeamFreeWill);
        ackreman = findViewById(R.id.radioAckerman);
        unagi = findViewById(R.id.radioUnagi);
        groupOfRadios = findViewById(R.id.radioGroupForTeams);
        getTeamData();
    }

    @Override
    public void onStart() {
        super.onStart();
        submit.setOnClickListener(view -> {
            Toast.makeText(this, "Submitted", Toast.LENGTH_SHORT).show();
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

        groupOfRadios.setOnClickListener(this::onRadioButtonClicked);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    @SuppressLint("NonConstantResourceId")
    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()) {
            case R.id.radioTeamFreeWill:
                if (checked)
                    name = "TeamFreeWill";
                System.out.println(name + "***************************");
                    break;
            case R.id.radioAckerman:
                if (checked)
                    name = "Ackreman";
                System.out.println(name);
                    break;
            case R.id.radioUnagi:
                if (checked)
                    name = "Unagi";
                System.out.println(name);
                break;
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
}
