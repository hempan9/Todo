package com.hmp.todo;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;

import com.hmp.todo.model.Todo;
import com.hmp.todo.storage.TodoStorage;

import java.sql.Date;
import java.time.Instant;

public class AddActivity extends AppCompatActivity {
    private EditText name;
    private EditText desc;
    private Button button;
    private Todo todo;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        name = findViewById(R.id.txt_name);
        name.setMaxLines(1);
        name.setImeOptions(EditorInfo.IME_ACTION_NEXT);

        desc =  findViewById(R.id.txt_desc);
        desc.setImeOptions(EditorInfo.IME_ACTION_NEXT);

        button = findViewById(R.id.save_button);

        button.setOnClickListener(
                (v) -> {
                    validateAndSaveTodo();
                    TodoStorage.saveTodo(todo);
                    startActivity(new Intent(this, MainActivity.class));
                }
        );
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void validateAndSaveTodo(){
        todo = new Todo();
        todo.setName(name.getText().toString());
        todo.setDesc(desc.getText().toString());
        todo.setCreatedDate(Date.from(Instant.now()));
    }
}