package com.example.to_do_app_v01;

public class todoData {
    String todoItem;
    int edit,delete;


    public todoData(String todoItem, int edit, int delete){
        this.todoItem = todoItem;
        this.delete = delete;
        this.edit = edit;

    }
}
