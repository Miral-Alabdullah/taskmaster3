//package com.example.taskmasterver2;
////
////import android.support.test.espresso.ViewAction;
////import android.support.test.espresso.contrib.RecyclerViewActions;
////
////import androidx.test.espresso.action.ViewActions;
////import androidx.test.ext.junit.rules.ActivityScenarioRule;
//////
//////import static androidx.test.espresso.Espresso.onView;
//////import static android.support.test.espresso.action.ViewActions.click;
//////import static android.support.test.espresso.assertion.ViewAssertions.matches;
//////import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
//////import static android.support.test.espresso.matcher.ViewMatchers.withId;
//////import static android.support.test.espresso.matcher.ViewMatchers.withText;
//////import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
////
////
////import static android.support.test.espresso.action.ViewActions.click;
////import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
////import static androidx.test.espresso.Espresso.onView;
//////import static androidx.test.espresso.action.ViewActions.click;
////import static androidx.test.espresso.assertion.ViewAssertions.matches;
////import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
////import static androidx.test.espresso.matcher.ViewMatchers.withId;
////import static androidx.test.espresso.matcher.ViewMatchers.withText;
//
//import android.support.test.espresso.ViewAction;
//import android.support.test.espresso.contrib.RecyclerViewActions;
//
//import androidx.test.ext.junit.rules.ActivityScenarioRule;
//
//import org.junit.Rule;
//import org.junit.Test;
//
//import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
//import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
//import static androidx.test.espresso.Espresso.onView;
//import static android.support.test.espresso.action.ViewActions.click;
//import static androidx.test.espresso.action.ViewActions.click;
//import static androidx.test.espresso.assertion.ViewAssertions.matches;
//import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
//import static androidx.test.espresso.matcher.ViewMatchers.withId;
//import static androidx.test.espresso.matcher.ViewMatchers.withText;
//
//public class RecyclerViewTest {
//    @Rule
//    public ActivityScenarioRule<MainActivity> mainActivityActivityScenarioRule
//            = new ActivityScenarioRule<>(MainActivity.class);
//
//    @Test
//    public void testClickItem() {
//        onView(withId(R.id.tasksRecyclerView))
//                .perform(actionOnItemAtPosition(0, click()));
//        AppDatabase appDatabase = AppDatabase.getTheInstance(getApplicationContext());
//        TaskDao taskDao = appDatabase.taskDao();
//        onView(withText(taskDao.getAllTasks().get(0).title)).check(matches(isDisplayed()));
//    }
//
//}
//
