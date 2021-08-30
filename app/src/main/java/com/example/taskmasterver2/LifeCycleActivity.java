package com.example.taskmasterver2;


import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LifeCycleActivity extends AppCompatActivity {

    private static final String TAG = "LifecycleActivity";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, "onCreate Called");
    }

    @Override
    public void onStart() {
        super.onStart();

        Log.d(TAG, "onStart Called");
    }

    @Override
    public void onResume() {
        super.onResume();

        Log.d(TAG, "onResume Called");
    }

    @Override
    public void onPause() {
        super.onPause();

        Log.d(TAG, "onPause Called");
    }

    @Override
    public void onStop() {
        super.onStop();

        Log.d(TAG, "onStop called");
    }

    @Override
    public void onRestart() {
        super.onRestart();

        Log.d(TAG, "onRestart: called");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.d(TAG, "onDestroy: called");
    }
}
