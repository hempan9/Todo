package com.hmp.todo.storage;

import android.util.Log;

import com.hmp.todo.model.Todo;

import java.util.ArrayList;
import java.util.List;

public class TodoStorage {
    private static final String TAG = "ERROR";
    private static List<Todo> todoList = new ArrayList<>();

    public static List<Todo> getToDoList() {
        Log.d("Fetched: " , "List of tod's");
        return todoList;
    }

    public static void saveTodo(Todo todo) {
        if (todo.getName().isEmpty()) {
            Log.e(TAG, "saveTodo: failed to save", null);
        } else {
            Log.d(TAG, "saveTodo: saved");
            todoList.add(todo);
        }
    }
    public static void deleteByIndex(int index) {
        todoList.remove(index);
        Log.d(TAG, "deleteByIndex: removed "+ index);
    }
    public static Todo findByIndex(int index) {
        if (index<=todoList.size()){
            Log.d(TAG, "findByIndex: found "+ index);
           return todoList.get(index);
        }
        else {
            throw new ArrayIndexOutOfBoundsException("No such index");
        }
    }
    public void updateTodo(int index, final Todo todo){
//        Todo todo1 = findByIndex(index);
//        todo1.setName(todo.getName());
//        todo1
    }
}
