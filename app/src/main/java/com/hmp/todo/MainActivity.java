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
import com.hmp.todo.storage.TodoStorage;

public class MainActivity extends AppCompatActivity {

    private ListAdapter listAdapter;

    @SuppressLint("ResourceType")
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = findViewById(R.id.todo_list);
        listAdapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, TodoStorage.getToDoList());
        listView.setOnItemClickListener((parent, view, position, id) -> startActivity(new Intent(MainActivity.this, AddActivity.class)));
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
                Toast.makeText( this,"HMP SOFT INC, 2022 (C)", Toast.LENGTH_LONG).show();
                return true;
            default:
                return false;
        }
    }

}