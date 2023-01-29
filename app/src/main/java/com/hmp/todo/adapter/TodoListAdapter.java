package com.hmp.todo.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.hmp.todo.R;
import com.hmp.todo.entity.TodoEntity;

import java.util.ArrayList;
import java.util.Date;

public class TodoListAdapter extends ArrayAdapter<TodoEntity> {

    private int resource;
    private Context context;
    private LayoutInflater layoutInflater;

    private static final String TAG = "TodoListAdapter";

    public TodoListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<TodoEntity> objects) {
        super(context, resource, objects);
        this.resource = resource;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // convertView which is recyclable view
        View currentItemView = convertView;

        // of the recyclable view is null then inflate the custom layout for the same
        if (currentItemView == null) {
            currentItemView = LayoutInflater.from(this.context).inflate(R.layout.custom_list_view, parent, false);
        }
        String name = getItem(position).getName();
        String desc = getItem(position).getDesc();
        Date date = getItem(position).getCreatedDate();

        TextView textViewName = (TextView) currentItemView.findViewById(R.id.todo_name);
        TextView textViewDesc = (TextView) currentItemView.findViewById(R.id.todo_desc);
        TextView textViewDate= (TextView) currentItemView.findViewById(R.id.todo_created_date);
        textViewName.setText(name);
        textViewDesc.setText(desc);
        textViewDate.setText(date.toString());
        return currentItemView;
    }

}
