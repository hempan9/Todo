package com.hmp.todo.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.hmp.todo.R;
import com.hmp.todo.entity.TodoEntity;
import com.hmp.todo.model.Todo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TodoListAdapter extends ArrayAdapter<TodoEntity> {

    private int resource;
    private Context context;
    private LayoutInflater layoutInflater;

    public TodoListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<TodoEntity> objects) {
        super(context, resource, objects);
        this.resource = resource;
        this.context = context;
    }

//    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // convertView which is recyclable view
        View currentItemView = convertView;

        // of the recyclable view is null then inflate the custom layout for the same
        if (currentItemView == null) {
            currentItemView = LayoutInflater.from(context).inflate(R.layout.custom_list_view, parent, false);
        }
        String name = getItem(position).getName();
        String desc = getItem(position).getDesc();
        Date date = getItem(position).getCreatedDate();
        TodoEntity todoEntity = new TodoEntity();
        todoEntity.setCreatedDate(date);
        todoEntity.setName(name);
        todoEntity.setDesc(desc);

        TextView textView1 = (TextView) currentItemView.findViewById(R.id.text1);
        TextView textView2 = (TextView) currentItemView.findViewById(R.id.text2);
        TextView textView3 = (TextView) currentItemView.findViewById(R.id.text3);
        textView1.setText(desc);
        textView2.setText(name);
        textView3.setText(date.toString());
        return currentItemView;
    }

}
