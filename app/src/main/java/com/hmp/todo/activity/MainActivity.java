package com.hmp.todo.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.hmp.todo.R;
import com.hmp.todo.adapter.TodoListAdapter;
import com.hmp.todo.entity.TodoEntity;
import com.hmp.todo.util.Util;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ArrayAdapter listAdapter;
    private static int TODO_COUNT = 0;
    private TextView textViewTodo;

    private static String TAG = "MainActivity:: ";

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = findViewById(R.id.todo_list);
        List<TodoEntity> db_todos = Util.createDb(this).todoDao().getAllTodos();
        textViewTodo = findViewById(R.id.to_);
        TODO_COUNT = db_todos.size();
        // show count for todos
        textViewTodo.setText("Todo(" + TODO_COUNT + ")");

        if (db_todos.isEmpty()) {
            listAdapter = new TodoListAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, new ArrayList<>());
        } else {
            ArrayList<TodoEntity> todoList = new ArrayList<>();
            db_todos.stream().forEach(
                    data -> todoList.add(data)
            );
            listAdapter = new TodoListAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, todoList);
            listView.setAdapter(listAdapter);
            listView.setOnItemClickListener((parent, view, position, id) -> {
                Intent intent = new Intent(MainActivity.this, UpdateActivity.class);
                intent.putExtra(Intent.EXTRA_TEXT, String.valueOf(id));
                startActivity(intent);
            });
            listView.setItemsCanFocus(true);
            listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                    Toast.makeText(MainActivity.this, "Long press", Toast.LENGTH_SHORT).show();
                    return false;
                }
            });

            findViewById(R.id.btn_add).setOnClickListener(
                    (view) -> startActivity(new Intent(this, AddActivity.class)));
        }
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
                Toast.makeText(this, "HMP SOFT INC, 2022 (C)", Toast.LENGTH_LONG).show();
                return true;
            case R.id.help:
                Toast.makeText(MainActivity.this, "Under Implementation", Toast.LENGTH_LONG).show();
            default:
                return false;
        }
    }
}