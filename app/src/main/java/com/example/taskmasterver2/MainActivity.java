package com.example.taskmasterver2;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobile.client.Callback;
import com.amazonaws.mobile.client.UserStateDetails;
import com.amazonaws.mobile.config.AWSConfiguration;
import com.amazonaws.mobileconnectors.pinpoint.PinpointConfiguration;
import com.amazonaws.mobileconnectors.pinpoint.PinpointManager;
import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.TaskGenerated;
import com.amplifyframework.storage.s3.AWSS3StoragePlugin;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.messaging.FirebaseMessaging;


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
    ArrayList<TaskGenerated> fetchTasks = new ArrayList<>();

    public static final String TAG = MainActivity.class.getSimpleName();

    private static PinpointManager pinpointManager;

    public static PinpointManager getPinpointManager(final Context applicationContext) {
        if (pinpointManager == null) {
            final AWSConfiguration awsConfig = new AWSConfiguration(applicationContext);
            AWSMobileClient.getInstance().initialize(applicationContext, awsConfig, new Callback<UserStateDetails>() {
                @Override
                public void onResult(UserStateDetails userStateDetails) {
                    Log.i("INIT", userStateDetails.getUserState().toString());
                }

                @Override
                public void onError(Exception e) {
                    Log.e("INIT", "Initialization error.", e);
                }
            });

            PinpointConfiguration pinpointConfig = new PinpointConfiguration(
                    applicationContext,
                    AWSMobileClient.getInstance(),
                    awsConfig);

            pinpointManager = new PinpointManager(pinpointConfig);

            FirebaseMessaging.getInstance().getToken()
                    .addOnCompleteListener(new OnCompleteListener<String>() {
                        @Override
                        public void onComplete(@NonNull com.google.android.gms.tasks.Task<String> task) {
                            if (!task.isSuccessful()) {
                                Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                                return;
                            }
                            final String token = task.getResult();
                            Log.d(TAG, "Registering push notifications token: " + token);
                            pinpointManager.getNotificationClient().registerDeviceToken(token);
                        }
                    });
        }
        return pinpointManager;
    }

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
        taskRecyclerView = findViewById(R.id.tasksRecyclerView);



        try {
            Amplify.addPlugin(new AWSApiPlugin());
            Amplify.addPlugin(new AWSCognitoAuthPlugin());
            Amplify.addPlugin(new AWSS3StoragePlugin());
            Amplify.configure(getApplicationContext());
            getPinpointManager(getApplicationContext());

            Log.i("MyAmplifyApp", "Initialized Amplify");
        } catch (AmplifyException error) {
            Log.e("MyAmplifyApp", "Could not initialize Amplify", error);
        }


        signInWithWeb();
        userNameText.setText(Amplify.Auth.getCurrentUser().getUsername());

    }

    @Override
    public void onStart() {
        super.onStart();
        Toast.makeText(getApplicationContext(), "onStart()", Toast.LENGTH_SHORT).show();

        setTaskRecyclerView(fetchTasks);
        fetchTheData(fetchTasks);


        /* ------------------------ Buttons With Listeners ------------------------ */
        toAddTask.setOnClickListener(view -> toAddTaskListener());
        toAllTasks.setOnClickListener(view -> toAllTasksListener());
        settings.setOnClickListener(view -> settingsListener());
        problemSolving.setOnClickListener(view-> toProblemSolvingListener());
        criticalThinking.setOnClickListener(view-> toCriticalThinkingListener());
        timeManagement.setOnClickListener(view-> toTimeManagementListener());


        signOut.setOnClickListener(view -> {
            Amplify.Auth.signOut(
                    () -> Log.i("AuthQuickstart", "Signed out successfully"),
                    error -> Log.e("AuthQuickstart", error.toString())
            );
        });


    }

    @Override
    protected void onResume() {
        super.onResume();


    }


    /************************************************************************************************
                                     - On Click Methods -
    ************************************************************************************************/

    private void toAddTaskListener(){
        Intent firstIntent = new Intent(MainActivity.this, AddTheTask.class);
        startActivity(firstIntent);
        Toast.makeText(MainActivity.this, "This button was clicked", Toast.LENGTH_SHORT).show();
    }

    private void toAllTasksListener(){
        Toast.makeText(MainActivity.this, "This button was clicked", Toast.LENGTH_SHORT).show();
        Intent toAllTasksIntent = new Intent(MainActivity.this, AllTasks.class);
        startActivity(toAllTasksIntent);
    }

    private void settingsListener(){
        Toast.makeText(MainActivity.this, "This button was clicked", Toast.LENGTH_SHORT).show();
        Intent gotToSettings = new Intent(MainActivity.this, SettingsPage.class);
        startActivity(gotToSettings);
    }

    private void setTaskRecyclerView(ArrayList<TaskGenerated> fetchTasks){
        taskRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        taskRecyclerView.setAdapter(new TaskAdapter(fetchTasks));
    }

    private void toProblemSolvingListener(){
        Toast.makeText(MainActivity.this, "This button was clicked", Toast.LENGTH_SHORT).show();
        String title = problemSolving.getText().toString();
        Intent goToTaskDetailPage = new Intent(MainActivity.this, TaskDetailPage.class);
        goToTaskDetailPage.putExtra("titlePass", title);
        startActivity(goToTaskDetailPage);
    }

    private void toCriticalThinkingListener(){
        Toast.makeText(MainActivity.this, "This button was clicked", Toast.LENGTH_SHORT).show();
        String title = criticalThinking.getText().toString();
        Intent goToTaskDetailPage = new Intent(MainActivity.this, TaskDetailPage.class);
        goToTaskDetailPage.putExtra("titlePass", title);
        startActivity(goToTaskDetailPage);
    }

    private void toTimeManagementListener(){
        Toast.makeText(MainActivity.this, "This button was clicked", Toast.LENGTH_SHORT).show();
        String title = timeManagement.getText().toString();
        Intent goToTaskDetailPage = new Intent(MainActivity.this, TaskDetailPage.class);
        goToTaskDetailPage.putExtra("titlePass", title);
        startActivity(goToTaskDetailPage);
    }



    /************************************************************************************************
                                     - Dealing With Amplify Methods -
     ************************************************************************************************/

    private void addPlugins(){
        try {
            Amplify.addPlugin(new AWSApiPlugin());
            Amplify.addPlugin(new AWSCognitoAuthPlugin());
            Amplify.configure(getApplicationContext());

            Log.i("MyAmplifyApp", "Initialized Amplify");
        } catch (AmplifyException error) {
            Log.e("MyAmplifyApp", "Could not initialize Amplify", error);
        }
    }


    private void signInWithWeb(){
        Amplify.Auth.signInWithWebUI(
                this,
                result -> Log.i("AuthQuickStart", result.toString()),
                error -> Log.e("AuthQuickStart", error.toString())
        );
    }


    public void fetchTheData(ArrayList<TaskGenerated> fetchTasks){
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

}
