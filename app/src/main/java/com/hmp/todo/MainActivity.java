package com.hmp.todo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.hmp.todo.model.Todo;
import com.hmp.todo.storage.TodoStorage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListAdapter listAdapter;
    private static int TODO_COUNT=0;
    private TextView textViewTodo;

    @SuppressLint("ResourceType")
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = findViewById(R.id.todo_list);
        List<Todo> todoList = TodoStorage.getToDoList();
        textViewTodo= findViewById(R.id.to_);
        TODO_COUNT = todoList.size();
        // show count for todos
        textViewTodo.setText("Todo("+TODO_COUNT+")");

        if (todoList.isEmpty()) {
            listAdapter = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, Collections.singletonList("No todo's found"));
        } else {
            listAdapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, todoList);
            listView.setOnItemClickListener((parent, view, position, id) -> {
                Intent intent = new Intent(MainActivity.this, UpdateActivity.class);
                intent.putExtra(Intent.EXTRA_TEXT, String.valueOf(position));
                startActivity(intent);
            });
        }

        listView.setAdapter(listAdapter);
        findViewById(R.id.btn_add).setOnClickListener(
                (view) -> startActivity(new Intent(this, AddActivity.class)));
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = new MenuInflater(MainActivity.this);
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    /**
     * For menus @override
     * onOptionMenuSelected method
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.setting:
                startActivity(new Intent(this, SettingActivity.class));
                return true;
            case R.id.about:
//          todo      startActivity();
                Toast.makeText(this, "HMP SOFT INC, 2022 (C)", Toast.LENGTH_LONG).show();
                return true;
            default:
                return false;
        }
    }

}