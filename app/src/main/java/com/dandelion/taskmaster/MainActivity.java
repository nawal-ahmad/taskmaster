package com.dandelion.taskmaster;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.*;
import android.widget.Button;
import android.widget.TextView;
import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Task;
import com.amplifyframework.datastore.generated.model.Team;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        try {
            Amplify.addPlugin(new AWSApiPlugin());
            Amplify.configure(getApplicationContext());

            Log.i("Main Activity", "Initialized Amplify");
        } catch (AmplifyException error) {
            Log.e("Main Activity", "Could not initialize Amplify", error);
        }


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

        Button settings = findViewById(R.id.Settings);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToSettings = new Intent(MainActivity.this, Settings.class);
                startActivity(goToSettings);
            }
        });


        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        String team = sharedPreferences.getString("team", "team");
        RecyclerView myTasks = findViewById(R.id.recycle);

        List<Team> teams = new ArrayList<>();
        List<Task> tasks = new ArrayList<>();



        Handler handler = new Handler(Looper.myLooper(), new Handler.Callback() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public boolean handleMessage(@NonNull Message msg) {

                myTasks.getAdapter().notifyDataSetChanged();
                return false;
            }
        });

        myTasks.setLayoutManager(new LinearLayoutManager(this));
        myTasks.setAdapter(new TaskAdapter(tasks));



        Amplify.API.query(
                ModelQuery.list(Team.class),
                response -> {
                    for (Team team1 : response.getData()) {
//                        Task taskOrg = new Task(todo.getTitle(), todo.getBody(), todo.getState());
                        Log.i("graph testing", team1.getName());
                        Log.i("graph testing", team1.getId());

                        teams.add(team1);
                    }
                    for(int i =0 ; i< teams.size();i++){
                        if (teams.get(i).getName().equals(team)){
                            tasks.addAll(teams.get(i).getTasks());
                            break;
                        }
                    }
                    handler.sendEmptyMessage(1);
                    Log.i("MyAmplifyApp", "out the loop");
                },
                error -> Log.e("MyAmplifyApp", "Query failure", error)
        );


    }

    @Override
    protected void onResume(){
        super.onResume();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        String username = sharedPreferences.getString("username","user");
        String team = sharedPreferences.getString("team", "team");

        TextView usernameField = findViewById(R.id.FirstText);
        usernameField.setText(username + "'s Tasks");

        TextView teamName = findViewById(R.id.teamName);
        teamName.setText(team);

    }
}