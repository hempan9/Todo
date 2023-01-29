package com.hmp.todo.activity;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.icu.util.GregorianCalendar;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.hmp.todo.R;
import com.hmp.todo.entity.TodoEntity;
import com.hmp.todo.model.Todo;
import com.hmp.todo.storage.TodoDatabase;
import com.hmp.todo.util.Util;

import java.time.Instant;
import java.util.Date;

public class AddActivity extends AppCompatActivity {
    private EditText name;
    private EditText desc;
    private TextView timeView;
    private Button buttonSave, timePicker;
    private Todo todo;
    private TodoEntity todoEntity;
    private static final String TAG = "AddActivity";

    Calendar cal = new GregorianCalendar();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        name = findViewById(R.id.txt_name_add);
        name.setMaxLines(1);
        name.setImeOptions(EditorInfo.IME_ACTION_NEXT);
        timeView = findViewById(R.id.selectedTime);

        desc = findViewById(R.id.txt_desc_add);
        desc.setImeOptions(EditorInfo.IME_ACTION_NEXT);

        buttonSave = findViewById(R.id.save_button);
        timePicker = findViewById(R.id.btn_timePicker_update);
        timePicker.setOnClickListener(
                v -> {
                    TimePickerDialog tp1 = new TimePickerDialog(this, this::onTimeSet, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true);
                    tp1.show();
                    cal.get(4);
                    timeView.setText("Time: " + cal.get(10) + ":" + cal.get(12));
                }
        );

        buttonSave.setOnClickListener(
                (v) -> {
                    if (validateAndSaveTodo()) {
                        TodoDatabase db = Util.createDb(this);
                        db.todoDao().saveTodo(todoEntity);
                        Log.d(TAG, "validateAndSaveTodo: TODO saved to database");
                        Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(this, MainActivity.class));
                    } else {
                        Toast.makeText(this, " Todo title cannot be empty", Toast.LENGTH_SHORT).show();
                    }

                }

        );
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
        cal.set(Calendar.MINUTE, minute);
    }

    public boolean validateAndSaveTodo() {
        if (name.getText().toString().isEmpty() || name.getText() == null) {
            Toast.makeText(this, "Title cannot be null/empty", Toast.LENGTH_LONG);
            return false;
        }
        todo = new Todo();
        todo.setName(name.getText().toString());
        todo.setDesc(desc.getText().toString());
        todo.setCreatedDate(Date.from(Instant.now()));
        todoEntity = new TodoEntity();
        todoEntity.setName(todo.getName());
        todoEntity.setDesc(todo.getDesc());
        todoEntity.setCreatedDate(todo.getCreatedDate());
        return true;
    }
}