package com.example.taskmasterver2;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Task.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    //Abstract function that returns the DAO
    public abstract TaskDao taskDao();

    //Create instance of the db class
    private static AppDatabase INSTANCE;

    //Function to return the instance
    public static AppDatabase getTheInstance(Context context){
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "Tasks")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }
}
