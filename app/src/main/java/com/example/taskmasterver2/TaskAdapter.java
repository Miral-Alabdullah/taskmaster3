package com.example.taskmasterver2;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.datastore.generated.model.TaskGenerated;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder>{

    List<TaskGenerated> tasks;

    public TaskAdapter (List<TaskGenerated> tasks){
        this.tasks = tasks;
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder{
        TaskGenerated task;
        View itemView;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            itemView.findViewById(R.id.layoutFrgmanet).setOnClickListener(view -> {
                Intent goToDetailPage = new Intent(view.getContext(), TaskDetailPage.class);
                goToDetailPage.putExtra("taskImage", task.getImage());
                goToDetailPage.putExtra("titlePass", task.getTitle());
                view.getContext().startActivity(goToDetailPage);
            });
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

        taskTitle.setText(holder.task.getTitle());
        taskBody.setText(holder.task.getBody());
        taskState.setText(holder.task.getState());


    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }
}

