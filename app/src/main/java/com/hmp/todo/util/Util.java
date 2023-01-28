package com.hmp.todo.util;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.hmp.todo.storage.TodoDatabase;

public class Util extends AppCompatActivity {
    private static String DATABASE_NAME = "todo-db";

    public static TodoDatabase createDb(Context context) {

        return Room.databaseBuilder(context, TodoDatabase.class, DATABASE_NAME)
                .allowMainThreadQueries().build();
    }

}
