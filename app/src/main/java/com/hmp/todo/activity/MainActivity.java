package com.hmp.todo.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.hmp.todo.R;
import com.hmp.todo.adapter.TodoListAdapter;
import com.hmp.todo.entity.TodoEntity;
import com.hmp.todo.model.Todo;
import com.hmp.todo.storage.TodoStorage;
import com.hmp.todo.util.Util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ArrayAdapter listAdapter;
    private static int TODO_COUNT = 0;
    private TextView textViewTodo;

    @SuppressLint("ResourceType")
    @RequiresApi(api = Build.VERSION_CODES.O)
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
            listAdapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, todoList);
            listView.setOnItemClickListener((parent, view, position, id) -> {
                Intent intent = new Intent(MainActivity.this, UpdateActivity.class);
                intent.putExtra(Intent.EXTRA_TEXT, String.valueOf(id));
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
                Toast.makeText(this, "HMP SOFT INC, 2022 (C)", Toast.LENGTH_LONG).show();
                return true;
            default:
                return false;
        }
    }

    public void loadData() {
//    SharedPreferences sharedPreferences = getApplicationContext().deleteSharedPreferences()
    }
}