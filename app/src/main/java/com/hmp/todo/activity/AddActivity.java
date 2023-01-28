package com.hmp.todo.activity;

import static android.content.ContentValues.TAG;

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
import com.hmp.todo.storage.TodoStorage;
import com.hmp.todo.util.Util;

import java.sql.Date;
import java.time.Instant;

@RequiresApi(api = Build.VERSION_CODES.N)
public class AddActivity extends AppCompatActivity {
    private EditText name;
    private EditText desc;
    private TextView timeView;
    private Button buttonSave, timePicker;
    private Todo todo;
    private TodoEntity todoEntity;

    Calendar cal = new GregorianCalendar();

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        name = findViewById(R.id.txt_name_update);
        name.setMaxLines(1);
        name.setImeOptions(EditorInfo.IME_ACTION_NEXT);
        timeView = findViewById(R.id.selectedTime);

        desc = findViewById(R.id.txt_desc_update);
        desc.setImeOptions(EditorInfo.IME_ACTION_NEXT);

        buttonSave = findViewById(R.id.update_button);
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
                    validateAndSaveTodo();
                    TodoStorage.saveTodo(todo);
                    TodoDatabase db = Util.createDb(this);
                    db.todoDao().saveTodo(todoEntity);
                    Log.d(TAG, "validateAndSaveTodo: TODO saved to database");

                    Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(this, MainActivity.class));
                }

        );
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
        cal.set(Calendar.MINUTE, minute);

        //rest of the code
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void validateAndSaveTodo() {
        todo = new Todo();
        todo.setName(name.getText().toString());
        todo.setDesc(desc.getText().toString());
        todo.setCreatedDate(Date.from(Instant.now()));
        todoEntity = new TodoEntity();
        todoEntity.setName(todo.getName());
        todoEntity.setDesc(todo.getDesc());
        todoEntity.setCreatedDate(todo.getCreatedDate());

    }
}