package com.example.taskmasterver2;


import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;



public class MainActivityTest {
    @Rule
    public ActivityScenarioRule<MainActivity> mainActivityActivityScenarioRule
            = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void headingIsVisible() {
        onView(withText("My Tasks")).check(matches(isDisplayed()));
    }


    @Test
    public void changeText_sameActivity() {
        onView(withId(R.id.toAddTaskBtn)).perform(click());
        onView(withId(R.id.editTextTaskTitle)).perform(typeText("Hello"), closeSoftKeyboard());
        onView(withId(R.id.editTextTaskDescription)).perform(typeText("New"), closeSoftKeyboard());
        onView(withId(R.id.editTextTaskState)).perform(typeText("World"), closeSoftKeyboard());

        pressBack();
        onView(ViewMatchers.withId(R.id.tasksRecyclerView)).check(matches(isDisplayed()));
    }

//    @Test
//    public void taskDetailsTest(){
//        onView(withId(R.id.tasksRecyclerView))
//                .perform(actionOnItemAtPosition(0, click()));
////        AppDB appDB = Room.databaseBuilder(getApplicationContext(), AppDB.class, "tasks").allowMainThreadQueries().build();
////        TaskDao taskDao = appDB.taskDao();
////        onView(withText(taskDao.getAllTasks().get(0).title)).check(matches(isDisplayed()));
//    }




}
