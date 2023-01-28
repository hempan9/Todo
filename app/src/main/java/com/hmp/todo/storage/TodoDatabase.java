package com.hmp.todo.storage;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.hmp.todo.config.Converters;
import com.hmp.todo.entity.TodoEntity;

@Database(entities = TodoEntity.class, version = 2)
@TypeConverters(Converters.class)
public abstract class TodoDatabase extends RoomDatabase {
    public abstract TodoDao todoDao();
}
