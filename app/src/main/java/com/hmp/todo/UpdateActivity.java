package com.hmp.todo;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.hmp.todo.model.Todo;
import com.hmp.todo.storage.TodoStorage;

import java.sql.Date;
import java.time.Instant;

public class UpdateActivity extends AppCompatActivity {
    private EditText name, desc;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        Intent intent = getIntent();
        int index = Integer.parseInt(intent.getStringExtra(Intent.EXTRA_TEXT));
        Todo todo = TodoStorage.findByIndex(index);
        name = findViewById(R.id.txt_name_update);
        name.setText(todo.getName());
        desc = findViewById(R.id.txt_desc_update);
        desc.setText(todo.getDesc());

        findViewById(R.id.update_button).setOnClickListener(
                v -> {
                    if (containsChange(todo)){
                        todo.setName(name.getText().toString());
                        todo.setDesc(desc.getText().toString());
                        todo.setCreatedDate(Date.from(Instant.now()));
                        Toast.makeText(this, "Todo updated", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(this, "No changes made", Toast.LENGTH_SHORT).show();

                    }

                    startActivity(new Intent(this, MainActivity.class));
                }
        );


    }

    private boolean containsChange(Todo todo) {
        if (todo.getName().equals(name.getText().toString()) && todo.getDesc().equals(desc.getText().toString())) {
            return false;
        }
        return true;
    }
}