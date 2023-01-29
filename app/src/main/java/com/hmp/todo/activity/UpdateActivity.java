package com.hmp.todo.activity;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.hmp.todo.R;
import com.hmp.todo.entity.TodoEntity;
import com.hmp.todo.util.Util;
;
import java.time.Instant;
import java.util.Date;

public class UpdateActivity extends AppCompatActivity {
    private EditText name, desc;
    private static final String TAG = "UpdateActivity:: ";
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
        // find text view by id for name
        name = findViewById(R.id.txt_name_update);
        name.setImeOptions(EditorInfo.IME_ACTION_NEXT);

        // set textview field from existing entity
        name.setText(todo.getName());
        desc = findViewById(R.id.txt_desc_update);
        desc.setImeOptions(EditorInfo.IME_ACTION_NEXT);
        desc.setText(todo.getDesc());
        findViewById(R.id.update_button).setOnClickListener(
                v -> {
                    if (containsChange(todo)) {
                        Log.d(TAG, "onCreate: name: "+name.getText()+"desc: "+ desc.getText());
                        todo.setName(name.getText().toString());
                        todo.setDesc(desc.getText().toString());
                        todo.setCreatedDate(Date.from(Instant.now()));
                        Log.d(TAG, "onCreate: saving to do in to database");
                        Util.createDb(this).todoDao().updateTodo(todo);
                        Log.d(TAG, "onCreate: successfully saved to do into the database. "+todo );
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
                Toast.makeText(this, "Todo successfully deleted", Toast.LENGTH_SHORT).show();
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