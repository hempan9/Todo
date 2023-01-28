package com.hmp.todo.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "Todo")
public class TodoEntity {
    @ColumnInfo(name = "todoId")
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String desc;
    private Date createdDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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


    public TodoEntity(int id, String name, String desc, Date createdDate) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.createdDate = createdDate;
    }

    public TodoEntity() {
    }

    @Override
    public String toString() {
        return "TodoEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", createdDate=" + createdDate +
                '}';
    }

}
