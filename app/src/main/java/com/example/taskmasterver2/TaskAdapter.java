package com.example.taskmasterver2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder>{

    RecyclerViewOnClickListener listener;
    List<Task> tasks;

    public TaskAdapter (List<Task> tasks){
        this.tasks = tasks;
        this.listener = listener;
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public Task task;
        View itemView;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
//            listener.onClick(view, getAdapterPosition());
        }
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_task_item, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskAdapter.TaskViewHolder holder, int position) {
        holder.task = tasks.get(position);

        TextView taskTitle = holder.itemView.findViewById(R.id.taskTitle);
        TextView taskBody = holder.itemView.findViewById(R.id.taskBody);
        TextView taskState = holder.itemView.findViewById(R.id.taskState);

        taskTitle.setText(holder.task.title);
        taskBody.setText(holder.task.body);
        taskState.setText(holder.task.state);
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public interface RecyclerViewOnClickListener{
        void onClick(View view, int position);
    }

}

