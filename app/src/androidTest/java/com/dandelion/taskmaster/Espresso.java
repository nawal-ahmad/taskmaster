package com.dandelion.taskmaster;



import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;

public class Espresso {
    @Rule
    public ActivityScenarioRule<MainActivity> rule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testAddTask() {
        onView(withId(R.id.addTask)).perform(click());
        onView(withId(R.id.taskTitle)).perform(typeText("Sleeping"),closeSoftKeyboard() );
        onView(withId(R.id.taskBody)).perform(typeText("all the night"),closeSoftKeyboard() );
        onView(withId(R.id.taskState)).perform(typeText("not yet"),closeSoftKeyboard() );
        onView(withId(R.id.addData)).perform(click());
//        onView(withText("Sleeping")).check(matches(isDisplayed()));
//        onView(withText("all the night")).check(matches(isDisplayed()));
//        onView(withText("not yet")).check(matches(isDisplayed()));
    }

    @Test
    public void testRecycleView() {
        onView(withId(R.id.recycle)).check(matches(isDisplayed()));
    }


    @Test
    public void testSettings() {
        onView(withId(R.id.Settings)).perform(click());
        onView(withId(R.id.nameField)).perform(typeText("Nawal"));
        onView(withId(R.id.userNameSave)).perform(click());
        onView(withId(R.id.FirstText)).check(matches(withText("Nawal's Tasks")));
    }

    @Test
    public void allTaskTest() {
        onView(withId(R.id.taskAll)).perform(click());
        onView(withId(R.id.allTaskData)).check(matches(isDisplayed()));
    }

    @Test
    public void checkTaskButton() {
        onView(withId(R.id.addTask)).perform(click());
        onView(withId(R.id.taskTitle)).check(matches(isDisplayed()));
        onView(withId(R.id.taskBody)).check(matches(isDisplayed()));
        onView(withId(R.id.taskState)).check(matches(isDisplayed()));
    }
}