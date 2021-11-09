package com.dandelion.taskmaster;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.datastore.generated.model.Task;
import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Team;

import java.util.ArrayList;
import java.util.List;

public class AddTask extends AppCompatActivity {
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    AppDatabase appDatabase;
    TaskDao taskDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        List<Team> teams = new ArrayList<>();

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



        EditText taskTitle = findViewById(R.id.taskTitle);
        EditText taskBody = findViewById(R.id.taskBody);
        EditText taskState = findViewById(R.id.taskState);


        appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "tasks").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        taskDao=appDatabase.taskDao();

        Button addTaskBtn = findViewById(R.id.addData);
        addTaskBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String title = taskTitle.getText().toString();
                String body = taskBody.getText().toString();
                String state = taskState.getText().toString();

                RadioButton firstRadio = findViewById(R.id.firstRadio);
                RadioButton secondRadio = findViewById(R.id.secondRadio);

                RadioButton thirdRadio = findViewById(R.id.thirdRadio);

                String name="";
                if(firstRadio.isChecked()) {
                    name="First";
                }else if(secondRadio.isChecked()){
                    name = "Second";
                }else if(thirdRadio.isChecked()){
                    name = "Third";
                }

                Team team=null;
                for (int i = 0; i < teams.size(); i++) {
                    if(teams.get(i).getName().equals(name)){
                        team = teams.get(i);
                    }
                }


                ///////////////////////////////////////////////32//////////////////////////////////////////
                Task task = Task.builder()
                        .title(title)
                        .body(body)
                        .state(state)
                        .team(team)
                        .build();

                // mutations are used to create, update, or delete data
                Amplify.API.mutate(
                        ModelMutation.create(task),
                        response -> Log.i("TasksApp", "add task with id" + response.getData().getId()),
                        error -> Log.e("TasksApp", "Create failed", error)
                );

                Intent goToHomePage = new Intent(AddTask.this, MainActivity.class);
                startActivity(goToHomePage);
            }
        });
    }
}



