package com.dandelion.taskmaster;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {
    List <Task> allTasks = new ArrayList<>();

    public TaskAdapter(List<Task> allTasks) {
        this.allTasks = allTasks;
    }


    public static class TaskViewHolder extends RecyclerView.ViewHolder{
        public Task task;
        View itemView;
        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;

            itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent goToTaskDetails = new Intent(v.getContext(), TaskDetail.class);
                        goToTaskDetails.putExtra("task", task.title+' '+task.body + ' '+ task.state);
                        v.getContext().startActivity(goToTaskDetails);
                    }
            });

        }
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_task,viewGroup,false);
        TaskViewHolder taskViewHolder = new TaskViewHolder(view);
        return taskViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TaskAdapter.TaskViewHolder taskViewHolder, int i) {
        taskViewHolder.task = allTasks.get(i);
//        Button fragment= taskViewHolder.itemView.findViewById(R.id.fragmentButton);
        TextView taskTitle = taskViewHolder.itemView.findViewById(R.id.taskTitleInFragment);
        TextView taskBody = taskViewHolder.itemView.findViewById(R.id.taskBodyInFragment);
        TextView taskState= taskViewHolder.itemView.findViewById(R.id.taskStateInFragment);
        taskTitle.setText(taskViewHolder.task.title);
        taskBody.setText(taskViewHolder.task.body);
        taskState.setText(taskViewHolder.task.state);
    }

    @Override
    public int getItemCount() {
        return allTasks.size();
    }

}
