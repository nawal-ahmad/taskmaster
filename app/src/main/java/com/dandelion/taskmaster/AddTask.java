package com.dandelion.taskmaster;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

public class AddTask extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        //////////////////////////////////// save to database ///////////////////////////////
        // get all edit text data
        EditText taskTitle = findViewById(R.id.taskTitle);
        EditText taskBody = findViewById(R.id.taskBody);
        EditText taskState = findViewById(R.id.taskState);
        // get add task button
        Button addTaskBtn = findViewById(R.id.addData);
        // add listener
        addTaskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppDatabase appDatabase;
                String title = taskTitle.getText().toString();
                String body = taskBody.getText().toString();
                String state = taskState.getText().toString();

                appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "tasks").allowMainThreadQueries().fallbackToDestructiveMigration().build();

                // save input fields into object
                Task task = new Task(title,body,state);
                // save to db
                appDatabase.taskDao().insertAll(task);
                // redirect to menu page
                Intent goToHomePage = new Intent(AddTask.this, MainActivity.class);
                startActivity(goToHomePage);
            }
        });


    }

//        TextView textView = findViewById(R.id.textView5);
//        Button addTask = findViewById(R.id.add);
//        addTask.setOnClickListener(new View.OnClickListener() {
//            int counter = 1 ;
//            @SuppressLint("SetTextI18n")
//            @Override
//            public void onClick(View v) {
//                textView.setText("Total Tasks :"+ counter++);
//                Toast.makeText(getApplicationContext(), "Submitted!", Toast.LENGTH_LONG).show();
//            }
//        });
//    }
}



