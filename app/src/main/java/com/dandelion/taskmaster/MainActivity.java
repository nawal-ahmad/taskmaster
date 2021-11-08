package com.dandelion.taskmaster;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addTask = findViewById(R.id.addTask);
        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToAddTask = new Intent(MainActivity.this, AddTask.class);
                startActivity(goToAddTask);
            }
        });

        Button allTasks = findViewById(R.id.taskAll);
        allTasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToAllTasks = new Intent(MainActivity.this, AllTasks.class);
                startActivity(goToAllTasks);
            }
        });

        Button task1 = findViewById(R.id.btn1ToDetails);
        task1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taskTitle= task1.getText().toString();
                Intent goToDetails = new Intent(MainActivity.this, TaskDetail.class);
                goToDetails.putExtra("title",taskTitle);
                startActivity(goToDetails);
            }
        });

        Button task2 = findViewById(R.id.btn2ToDetails);
        task2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taskTitle = task2.getText().toString();
                Intent goToDetails = new Intent(MainActivity.this, TaskDetail.class);
                goToDetails.putExtra("title",taskTitle);
                startActivity(goToDetails);
            }
        });

        Button task3 = findViewById(R.id.btn3ToDetails);
        task3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taskTitle = task3.getText().toString();
                Intent goToDetails = new Intent(MainActivity.this, TaskDetail.class);
                goToDetails.putExtra("title",taskTitle);
                startActivity(goToDetails);
            }
        });

        Button settings = findViewById(R.id.Settings);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToSettings = new Intent(MainActivity.this, Settings.class);
                startActivity(goToSettings);
            }
        });
        /////////////////////////////////////////lab28//////////////////////////////////////////////
        // Create some data
//        List<Task> tasks = new ArrayList<>();
//        tasks.add(new Task("Task1","Task1 body", "new"));
//        tasks.add(new Task("Task2","Task2 body", "assigned"));
//        tasks.add(new Task("Task3","Task3 body", "complete"));
//        // get the recycler view
//        RecyclerView tasksRecyclerView= findViewById(R.id.recycle);
//        // set a layout manager for this view
//        tasksRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        // set the adapter for this recyclerView
//        tasksRecyclerView.setAdapter(new TaskAdapter(tasks));

        /////////////////////////////////////////lab29//////////////////////////////////////////////
        // tasks from database
        AppDatabase appDatabase;
        appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "tasks").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        List<Task> tasks = appDatabase.taskDao().getAll();

        // get recycler view
        RecyclerView allTasksRecyclerView = findViewById(R.id.recycle);

        // set layout manager for the view (determine if liner list or grid list)
        allTasksRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // set the adapter for this recycler
        allTasksRecyclerView.setAdapter(new TaskAdapter(tasks));
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        String username = sharedPreferences.getString("username","user");

        TextView usernameField = findViewById(R.id.FirstText);
        usernameField.setText(username + "'s Tasks");
    }
}