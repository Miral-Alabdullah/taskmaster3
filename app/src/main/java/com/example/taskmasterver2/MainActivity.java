//package com.example.taskmasterver2;
//
//
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Looper;
//import android.preference.PreferenceManager;
//import android.util.Log;
//import android.widget.Button;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.amplifyframework.AmplifyException;
//import com.amplifyframework.api.aws.AWSApiPlugin;
//import com.amplifyframework.api.graphql.model.ModelQuery;
//import com.amplifyframework.auth.AuthUserAttributeKey;
//import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
//import com.amplifyframework.auth.options.AuthSignUpOptions;
//import com.amplifyframework.core.Amplify;
//import com.amplifyframework.datastore.generated.model.TaskGenerated;
//
//import java.util.ArrayList;
//
//public class MainActivity extends AppCompatActivity {
//
//    Button
//            toAddTask,
//            toAllTasks,
//            settings,
//            problemSolving,
//            criticalThinking,
//            timeManagement,
//            signOut;
//
//    TextView userNameText;
//    RecyclerView taskRecyclerView;
//    ArrayList<TaskGenerated> fetchTasks = new ArrayList<>();
//
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        Toast.makeText(getApplicationContext(), "onCreate()", Toast.LENGTH_SHORT).show();
//        toAddTask = findViewById(R.id.toAddTaskBtn);
//        toAllTasks = findViewById(R.id.toSeeTheTasksBtn);
//        settings = findViewById(R.id.settingsBtn);
//        problemSolving = findViewById(R.id.solvingBtn);
//        criticalThinking = findViewById(R.id.criticalBtn);
//        timeManagement = findViewById(R.id.timeBtn);
//        userNameText = findViewById(R.id.userNameHome);
//        signOut = findViewById(R.id.siginingOut);
//        taskRecyclerView = findViewById(R.id.tasksRecyclerView);
//
//
//
//        addPlugins();
//
//        signInWithWeb();
//
//    }
//
//    @Override
//    public void onStart() {
//        super.onStart();
//        Toast.makeText(getApplicationContext(), "onStart()", Toast.LENGTH_SHORT).show();
//
//        setTaskRecyclerView(fetchTasks);
//
//
//
//        /* ------------------------ Buttons With Listeners ------------------------ */
//        toAddTask.setOnClickListener(view -> toAddTaskListener());
//        toAllTasks.setOnClickListener(view -> toAllTasksListener());
//        settings.setOnClickListener(view -> settingsListener());
//        problemSolving.setOnClickListener(view-> toProblemSolvingListener());
//        criticalThinking.setOnClickListener(view-> toCriticalThinkingListener());
//        timeManagement.setOnClickListener(view-> toTimeManagementListener());
//
//
//        signOut.setOnClickListener(view -> {
//            Amplify.Auth.signOut(
//                    () -> Log.i("AuthQuickstart", "Signed out successfully"),
//                    error -> Log.e("AuthQuickstart", error.toString())
//            );
//        });
//
//
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        userNameText.setText(Amplify.Auth.getCurrentUser().getUsername());
//        fetchTheData(fetchTasks);
//
//    }
//
//
//    /************************************************************************************************
//                                     - On Click Methods -
//    ************************************************************************************************/
//
//    private void toAddTaskListener(){
//        Intent firstIntent = new Intent(MainActivity.this, AddTheTask.class);
//        startActivity(firstIntent);
//        Toast.makeText(MainActivity.this, "This button was clicked", Toast.LENGTH_SHORT).show();
//    }
//
//    private void toAllTasksListener(){
//        Toast.makeText(MainActivity.this, "This button was clicked", Toast.LENGTH_SHORT).show();
//        Intent toAllTasksIntent = new Intent(MainActivity.this, AllTasks.class);
//        startActivity(toAllTasksIntent);
//    }
//
//    private void settingsListener(){
//        Toast.makeText(MainActivity.this, "This button was clicked", Toast.LENGTH_SHORT).show();
//        Intent gotToSettings = new Intent(MainActivity.this, SettingsPage.class);
//        startActivity(gotToSettings);
//    }
//
//    private void setTaskRecyclerView(ArrayList<TaskGenerated> fetchTasks){
//        taskRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
//        taskRecyclerView.setAdapter(new TaskAdapter(fetchTasks));
//    }
//
//    private void toProblemSolvingListener(){
//        Toast.makeText(MainActivity.this, "This button was clicked", Toast.LENGTH_SHORT).show();
//        String title = problemSolving.getText().toString();
//        Intent goToTaskDetailPage = new Intent(MainActivity.this, TaskDetailPage.class);
//        goToTaskDetailPage.putExtra("titlePass", title);
//        startActivity(goToTaskDetailPage);
//    }
//
//    private void toCriticalThinkingListener(){
//        Toast.makeText(MainActivity.this, "This button was clicked", Toast.LENGTH_SHORT).show();
//        String title = criticalThinking.getText().toString();
//        Intent goToTaskDetailPage = new Intent(MainActivity.this, TaskDetailPage.class);
//        goToTaskDetailPage.putExtra("titlePass", title);
//        startActivity(goToTaskDetailPage);
//    }
//
//    private void toTimeManagementListener(){
//        Toast.makeText(MainActivity.this, "This button was clicked", Toast.LENGTH_SHORT).show();
//        String title = timeManagement.getText().toString();
//        Intent goToTaskDetailPage = new Intent(MainActivity.this, TaskDetailPage.class);
//        goToTaskDetailPage.putExtra("titlePass", title);
//        startActivity(goToTaskDetailPage);
//    }
//
//
//
//    /************************************************************************************************
//                                     - Dealing With Amplify Methods -
//     ************************************************************************************************/
//
//    private void addPlugins(){
//        try {
//            Amplify.addPlugin(new AWSApiPlugin());
//            Amplify.addPlugin(new AWSCognitoAuthPlugin());
//            Amplify.configure(getApplicationContext());
//
//            Log.i("MyAmplifyApp", "Initialized Amplify");
//        } catch (AmplifyException error) {
//            Log.e("MyAmplifyApp", "Could not initialize Amplify", error);
//        }
//    }
//
//
//    private void signInWithWeb(){
//        Amplify.Auth.signInWithWebUI(
//                this,
//                result -> Log.i("AuthQuickStart", result.toString()),
//                error -> Log.e("AuthQuickStart", error.toString())
//        );
//    }
//
//
//    public void fetchTheData(ArrayList<TaskGenerated> fetchTasks){
//        Handler handler = new Handler(Looper.myLooper(), message -> {
//            taskRecyclerView.getAdapter().notifyDataSetChanged();
//            return false;
//        });
//
//        Amplify.API.query(
//                ModelQuery.list(TaskGenerated.class),
//                response -> {
//                    for (TaskGenerated taskGenerated : response.getData()) {
//                        Log.i("MyAmplifyApp", taskGenerated.getTitle());
//                        Log.i("MyAmplifyApp", taskGenerated.getBody());
//                        Log.i("MyAmplifyApp", taskGenerated.getState());
//                        fetchTasks.add(taskGenerated);
//                    }
//                    handler.sendEmptyMessage(1);
//                },
//                error -> Log.e("MyAmplifyApp", "Query failure", error)
//        );
//    }
//
//}

package com.example.taskmasterver2;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.TaskGenerated;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button
            toAddTask,
            toAllTasks,
            settings,
            problemSolving,
            criticalThinking,
            timeManagement,
            signOut;

    TextView userNameText;
    RecyclerView taskRecyclerView;
//    TaskAdapter.RecyclerViewOnClickListener listener;
//    List<Task> tasks = new ArrayList<>();


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
        signOut = findViewById(R.id.siginingOut);



        try {
            Amplify.addPlugin(new AWSApiPlugin());
            Amplify.addPlugin(new AWSCognitoAuthPlugin());
            Amplify.configure(getApplicationContext());

            Log.i("MyAmplifyApp", "Initialized Amplify");
        } catch (AmplifyException error) {
            Log.e("MyAmplifyApp", "Could not initialize Amplify", error);
        }


//        tasks.add(new Task("Merge Sort", "Complete The Blog", "In Progress"));
//        tasks.add(new Task("Insertion Sort", "Add A Small Description For Each Step", "In Progress"));
//        tasks.add(new Task("Formatting", "Bring Your Laptop Back To Life", "New"));
//        tasks.add(new Task("RecyclerView", "Finish The Lab Requirements", "Completed"));


//        setOnClickListener();

        Amplify.Auth.signInWithWebUI(
                this,
                result -> Log.i("AuthQuickStart", result.toString()),
                error -> Log.e("AuthQuickStart", error.toString())
        );

    }

    @Override
    public void onStart() {
        super.onStart();
        Toast.makeText(getApplicationContext(), "onStart()", Toast.LENGTH_SHORT).show();
        toAddTask.setOnClickListener(view -> {
            Intent firstIntent = new Intent(MainActivity.this, AddTheTask.class);
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

        signOut.setOnClickListener(view -> {
            Amplify.Auth.signOut(
                    () -> Log.i("AuthQuickstart", "Signed out successfully"),
                    error -> Log.e("AuthQuickstart", error.toString())
            );
        });

        ///////////////////////////////////////////////////////////////////////////////////////////

    }

    @Override
    protected void onResume() {
        super.onResume();
        ArrayList<TaskGenerated> fetchTasks = new ArrayList<>();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        String userName = sharedPreferences.getString("userNameText", "");
        userNameText.setText(userName);

//        AppDatabase appDatabase = AppDatabase.getTheInstance(this.getApplicationContext());
//        tasks = appDatabase.taskDao().getAllTasks();

        taskRecyclerView = findViewById(R.id.tasksRecyclerView);
        taskRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        taskRecyclerView.setAdapter(new TaskAdapter(fetchTasks));

        Handler handler = new Handler(Looper.myLooper(), message -> {
            taskRecyclerView.getAdapter().notifyDataSetChanged();
            return false;
        });

        Amplify.API.query(
                ModelQuery.list(TaskGenerated.class),
                response -> {
                    for (TaskGenerated taskGenerated : response.getData()) {
                        Log.i("MyAmplifyApp", taskGenerated.getTitle());
                        Log.i("MyAmplifyApp", taskGenerated.getBody());
                        Log.i("MyAmplifyApp", taskGenerated.getState());
                        fetchTasks.add(taskGenerated);
                    }
                    handler.sendEmptyMessage(1);
                },
                error -> Log.e("MyAmplifyApp", "Query failure", error)
        );
    }

    ///////////////////////////////////////////////////////////////////////////////////////////

//    private void setOnClickListener(){
//        listener = (view, position) -> {
//            Intent intent = new Intent(getApplicationContext(), SingleTask.class);
//            intent.putExtra("task", tasks.get(position).title);
//            startActivity(intent);
//        };
//    }
}