package com.hmp.todo.storage;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.hmp.todo.entity.TodoEntity;

import java.util.List;

@Dao
public interface TodoDao {
    @Query(value = "SELECT * FROM todo")
    List<TodoEntity> getAllTodos();

    @Insert
    void saveTodo(TodoEntity entity);

    @Update
    void updateTodo(final TodoEntity todoEntity);

    @Query(value = "SELECT * FROM todo WHERE :id = todoId ")
    TodoEntity getTodoById(int id);

    @Delete
    void deleteTodo(TodoEntity todoEntity);
}
