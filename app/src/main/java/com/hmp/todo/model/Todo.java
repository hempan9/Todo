package com.hmp.todo.model;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import java.time.Instant;
import java.util.Date;

public class Todo {
    private String name;
    private String desc;
    private Date createdDate;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Todo() {
        this.name = "Buy laptop";
        this.desc = "Buy latop from ebay";
        this.createdDate = Date.from(Instant.now());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Todo(String name, String desc, Date createdDate) {
        this.name = name;
        this.desc = desc;
        this.createdDate = createdDate;
    }

    @NonNull
    @Override
    public String toString() {
        return this.name+"\n\t"+this.desc;
    }
}
