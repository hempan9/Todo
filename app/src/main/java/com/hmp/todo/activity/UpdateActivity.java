package com.hmp.todo.activity;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.hmp.todo.R;
import com.hmp.todo.entity.TodoEntity;
import com.hmp.todo.model.Todo;
import com.hmp.todo.storage.TodoDatabase;
import com.hmp.todo.storage.TodoStorage;
import com.hmp.todo.util.Util;

import java.sql.Date;
import java.time.Instant;

public class UpdateActivity extends AppCompatActivity {
    private EditText name, desc;

    @SuppressLint("MissingInflatedId")
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        Intent intent = getIntent();
        int index = Integer.parseInt(intent.getStringExtra(Intent.EXTRA_TEXT));
        TodoEntity todo = Util.createDb(this).todoDao().getAllTodos().get(index);
        Log.d(TAG, "onCreate: todo: {}" + todo + " index: " + index);
        name = findViewById(R.id.txt_name_update);
        name.setText(todo.getName());
        desc = findViewById(R.id.txt_desc_update);
        desc.setText(todo.getDesc());

        findViewById(R.id.update_button).setOnClickListener(
                v -> {
                    if (containsChange(todo)) {
                        todo.setName(name.getText().toString());
                        todo.setDesc(desc.getText().toString());
                        todo.setCreatedDate(Date.from(Instant.now()));
                        Toast.makeText(this, "Todo updated", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "No changes made", Toast.LENGTH_SHORT).show();

                    }

                    startActivity(new Intent(this, MainActivity.class));
                }
        );

        findViewById(R.id.delete_button).setOnClickListener(v -> {

            if (todo != null) {
                Util.createDb(this).todoDao().deleteTodo(todo);
                Log.d(TAG, "onCreate: Todo deleted successfully");
                startActivity(new Intent(this, MainActivity.class));
                Toast.makeText(this, "Todo successfully deleted made", Toast.LENGTH_SHORT).show();
            }

        });
    }

    private boolean containsChange(TodoEntity todo) {
        if (todo.getName().equals(name.getText().toString()) && todo.getDesc().equals(desc.getText().toString())) {
            return false;
        }
        return true;
    }
}