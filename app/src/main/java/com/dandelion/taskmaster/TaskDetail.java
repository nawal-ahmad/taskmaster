package com.dandelion.taskmaster;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TaskDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        Intent intent = getIntent();

        String title = intent.getExtras().getString("title");
        TextView titleText = findViewById(R.id.tasktitle);
        titleText.setText(title);

        String body = intent.getExtras().getString("body");
        TextView taskBody = findViewById(R.id.taskBody);
        taskBody.setText(body);

        String state = intent.getExtras().getString("state");
        TextView taskState = findViewById(R.id.taskState);
        taskState.setText(state);
    }
}